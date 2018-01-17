package cz.sajvera.ppro.bean;

import cz.sajvera.ppro.dao.KategorieDao;
import cz.sajvera.ppro.dao.ReceptDao;
import cz.sajvera.ppro.model.Kategorie;
import cz.sajvera.ppro.model.Recept;
import cz.sajvera.ppro.model.Surovina;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UpravaReceptuBean implements Serializable {

    @Inject
    private ReceptDao receptDao;

    private Recept recept;

    private int receptID;

    @Inject
    private KategorieDao kategorieDao;

    private List<Kategorie> kategorieList;

    private Surovina surovina;

    @Inject
    private PrihlaseniOdhlaseniBean prihlaseniOdhlaseniBean;

    @PostConstruct
    public void init() throws IOException {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("recept");
        receptID = Integer.parseInt(id);
        if(receptDao.jeIDvDB(receptID)) {
            if(receptDao.maUzivatelOpravneniKReceptu(receptID, prihlaseniOdhlaseniBean.getUzivatel())) {
                recept = receptDao.findReceptById(receptID);
                kategorieList = kategorieDao.findAll();
                surovina = new Surovina();
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../erroropravneni.xhtml");
            }
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../error.xhtml");
        }
    }

    public Recept getRecept() {
        return recept;
    }

    public void setRecept(Recept recept) {
        this.recept = recept;
    }

    public List<Kategorie> getKategorieList() {
        return kategorieList;
    }

    public void setKategorieList(List<Kategorie> kategorieList) {
        this.kategorieList = kategorieList;
    }

    public Surovina getSurovina() {
        return surovina;
    }

    public void setSurovina(Surovina surovina) {
        this.surovina = surovina;
    }

    public Kategorie getKategorie(Integer id) {
        if (id == null){
            throw new IllegalArgumentException("no id provided");
        }
        for (Kategorie k : kategorieList){
            if (id.equals(k.getId())){
                return k;
            }
        }
        return null;
    }

    public String reinitSurovina() {
        surovina.setRecept(recept);
        surovina = new Surovina();
        return null;
    }

    public String upravitRecept() {
        if(recept.getSuroviny().isEmpty()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nepřidány žádné suroviny!", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "";
        } else {
            //recept.setFotka(new Fotka("/resources/upload/MObr.635608233708013647.jpg", "/resources/upload/SObr.635608233708013647.jpg", "/resources/upload/VObr.635608233708013647.jpg"));
            receptDao.merge(recept);
            String zprava;
            if(recept.getNazev().length() > 15)
                zprava = "Recept " + recept.getNazev().substring(0, 14) + "... úspěšně upraven.";
            else
                zprava = "Recept " + recept.getNazev() + " úspěšně upraven.";
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, zprava, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "seznamreceptu?faces-redirect=true";
        }
    }

}
