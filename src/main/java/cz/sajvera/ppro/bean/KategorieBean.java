package cz.sajvera.ppro.bean;

import cz.sajvera.ppro.dao.KategorieDao;
import cz.sajvera.ppro.model.Kategorie;

import javax.annotation.PostConstruct;
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

    public List<Kategorie> getKategorieList() {
        return kategorieDao.findAll();
    }

    public Kategorie getKategorieByID() {
        return kategorieDao.findKategorieById(1);
    }

}
