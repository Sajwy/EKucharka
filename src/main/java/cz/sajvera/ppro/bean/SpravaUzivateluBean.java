package cz.sajvera.ppro.bean;

import cz.sajvera.ppro.dao.RoleDao;
import cz.sajvera.ppro.dao.UzivatelDao;
import cz.sajvera.ppro.model.Role;
import cz.sajvera.ppro.model.Uzivatel;
import org.primefaces.event.CellEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class SpravaUzivateluBean implements Serializable {

    @Inject
    private UzivatelDao uzivatelDao;

    private List<Uzivatel> uzivatele;

    @Inject
    private RoleDao roleDao;

    private List<Role> roleList;

    @PostConstruct
    public void init() {
        uzivatele = uzivatelDao.findAll();
        roleList = roleDao.findAll();
    }

    public List<Uzivatel> getUzivatele() {
        return uzivatele;
    }

    public void setUzivatele(List<Uzivatel> uzivatele) {
        this.uzivatele = uzivatele;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public void smazatUzivatele(Uzivatel u) {
        uzivatele.remove(u);
        //u.getRole().getUzivatele().remove(u);
        uzivatelDao.delete(u);
    }

    public void onCellEdit(CellEditEvent event) {
        int index = event.getRowIndex();
        String key = event.getRowKey();
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public Role getRole(Integer id) {
        if (id == null){
            throw new IllegalArgumentException("no id provided");
        }
        for (Role r : roleList){
            if (id.equals(r.getId())){
                return r;
            }
        }
        return null;
    }
}