package cz.sajvera.ppro.bean;

import cz.sajvera.ppro.dao.ReceptDao;
import cz.sajvera.ppro.model.Recept;

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
public class SpravaReceptuBean implements Serializable {

    @Inject
    private ReceptDao receptDao;

    private List<Recept> recepty;

    @PostConstruct
    public void init() {
        recepty = receptDao.findAll();
    }

    public List<Recept> getRecepty() {
        return recepty;
    }

    public void setRecepty(List<Recept> recepty) {
        this.recepty = recepty;
    }

    public void smazatRecept(Recept r) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Recept " + r.getNazev() + " smazán.", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
        recepty.remove(r);
        receptDao.delete(r);
    }

}
