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

    @Inject
    private PrihlaseniOdhlaseniBean prihlaseniOdhlaseniBean;

    @PostConstruct
    public void init() {
        uzivatele = uzivatelDao.findAllExceptMe(prihlaseniOdhlaseniBean.getUzivatel());
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
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Uživatel " + u.getUzivatelskeJmeno() + " smazán.", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        uzivatele.remove(u);
        uzivatelDao.delete(u);
    }

    public void onCellEdit(CellEditEvent event) {
        Role stara = (Role) event.getOldValue();
        Role nova = (Role) event.getNewValue();

        if(nova != null && !nova.equals(stara)) {
            Uzivatel uzivatel = uzivatele.get(event.getRowIndex());
            uzivatelDao.merge(uzivatel);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Role uživatele " + uzivatel.getUzivatelskeJmeno() + " změněna.", "Původní: " + stara.getNazev() + ", Nová: " + nova.getNazev() + ".");
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