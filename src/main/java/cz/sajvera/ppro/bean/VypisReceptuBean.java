package cz.sajvera.ppro.bean;

import cz.sajvera.ppro.dao.KategorieDao;
import cz.sajvera.ppro.model.Kategorie;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class VypisReceptuBean implements Serializable {

    @Inject
    private KategorieDao kategorieDao;

    private Kategorie kategorie;

    @PostConstruct
    public void init() throws IOException {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("kategorie");
        kategorieID = Integer.parseInt(id);
        if(kategorieDao.jeIDvDB(kategorieID))
            kategorie = kategorieDao.findKategorieById(kategorieID);
        else
            FacesContext.getCurrentInstance().getExternalContext().redirect("error.xhtml");
    }

    private int kategorieID;

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
}
