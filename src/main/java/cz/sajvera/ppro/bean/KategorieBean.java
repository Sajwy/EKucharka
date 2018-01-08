package cz.sajvera.ppro.bean;

import cz.sajvera.ppro.dao.KategorieDao;
import cz.sajvera.ppro.model.Kategorie;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class KategorieBean implements Serializable {
    @Inject
    private KategorieDao kategorieDao;
    //private List<Kategorie> buildings;

    public List<Kategorie> getBuildings() {
        return kategorieDao.findAll();
    }

    public Kategorie getKategorieByID() {
        return kategorieDao.findKategorieById(1);
    }

    public int getId() {
        return kategorieDao.findKategorieById(1).getId();
    }

    public String getNazev() {
        return kategorieDao.findKategorieById(1).getNazev();
    }
}
