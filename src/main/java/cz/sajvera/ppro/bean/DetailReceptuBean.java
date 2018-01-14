package cz.sajvera.ppro.bean;

import cz.sajvera.ppro.dao.ReceptDao;
import cz.sajvera.ppro.model.Recept;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class DetailReceptuBean implements Serializable {

    @Inject
    private ReceptDao receptDao;

    private int receptID;
    private Recept recept;

    @PostConstruct
    public void init() throws IOException {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("recept");
        receptID = Integer.parseInt(id);
        if(receptDao.jeIDvDB(receptID)) {
            recept = receptDao.findReceptById(receptID);
        } else
            FacesContext.getCurrentInstance().getExternalContext().redirect("error.xhtml");
    }

    public int getReceptID() {
        return receptID;
    }

    public void setReceptID(int receptID) {
        this.receptID = receptID;
    }

    public Recept getRecept() {
        return recept;
    }

    public void setRecept(Recept recept) {
        this.recept = recept;
    }
}
