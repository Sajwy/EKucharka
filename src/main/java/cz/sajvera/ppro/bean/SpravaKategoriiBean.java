package cz.sajvera.ppro.bean;

import cz.sajvera.ppro.dao.KategorieDao;
import cz.sajvera.ppro.model.Kategorie;
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
public class SpravaKategoriiBean implements Serializable {

    @Inject
    private KategorieDao kategorieDao;

    @Inject
    private KategorieBean kategorieBean;

    private List<Kategorie> kategorieList;
    private Kategorie kategorie;

    @PostConstruct
    public void init() {
        kategorieList = kategorieBean.getKategorieList(); //kategorieDao.findAll();
        kategorie = new Kategorie();
    }

    public List<Kategorie> getKategorieList() {
        return kategorieList;
    }

    public void setKategorieList(List<Kategorie> kategorieList) {
        this.kategorieList = kategorieList;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public String pridatKategorii() {
        kategorieDao.save(kategorie);
        kategorieList.add(kategorie);
        kategorie = new Kategorie();
        //message do growlu o přidání kategorie
        return null;
    }

    public void smazatKategorii(Kategorie k) {
        kategorieList.remove(k);
        kategorieDao.delete(k);
    }

    public void onCellEdit(CellEditEvent event) {
        String stara = (String) event.getOldValue();
        String nova = (String) event.getNewValue();

        if(nova != null && !nova.equals(stara)) {
            Kategorie kategorie = kategorieList.get(event.getRowIndex());
            kategorieDao.merge(kategorie);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Kategorie změněna.", "Původní název: " + stara + ", Nový název: " + nova + ".");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
