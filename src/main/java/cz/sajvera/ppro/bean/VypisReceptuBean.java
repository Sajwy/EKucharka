package cz.sajvera.ppro.bean;

import cz.sajvera.ppro.dao.KategorieDao;
import cz.sajvera.ppro.dao.ReceptDao;
import cz.sajvera.ppro.model.Kategorie;
import cz.sajvera.ppro.model.Recept;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class VypisReceptuBean implements Serializable {

    @Inject
    private KategorieDao kategorieDao;

    private Kategorie kategorie;

    private int kategorieID;

    @Inject
    private ReceptDao receptDao;

    private List<Recept> recepty;

    @PostConstruct
    public void init() throws IOException {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("kategorie");
        kategorieID = Integer.parseInt(id);
        if(kategorieDao.jeIDvDB(kategorieID)) {
            kategorie = kategorieDao.findKategorieById(kategorieID);
            recepty = receptDao.findReceptsByKategorieID(kategorieID);
        } else
            FacesContext.getCurrentInstance().getExternalContext().redirect("error.xhtml");
    }

    public int getKategorieID() {
        return kategorieID;
    }

    public void setKategorieID(int kategorieID) {
        this.kategorieID = kategorieID;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public List<Recept> getRecepty() {
        return recepty;
    }

    public void setRecepty(List<Recept> recepty) {
        this.recepty = recepty;
    }
}
