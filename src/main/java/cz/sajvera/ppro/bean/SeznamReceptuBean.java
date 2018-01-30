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
public class SeznamReceptuBean implements Serializable {

    @Inject
    private ReceptDao receptDao;

    private List<Recept> recepty;

    @Inject
    private PrihlaseniOdhlaseniBean prihlaseniOdhlaseniBean;

    @PostConstruct
    public void init() {
        recepty = receptDao.findReceptsByUzivatel(prihlaseniOdhlaseniBean.getUzivatel());
    }

    public List<Recept> getRecepty() {
        return recepty;
    }

    public void setRecepty(List<Recept> recepty) {
        this.recepty = recepty;
    }

    public void smazatRecept(Recept r) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Recept " + r.getNazev() + " odstraněn.", "Budeme však rádi, když přidáte nový.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        recepty.remove(r);
        receptDao.delete(r);
    }
}
