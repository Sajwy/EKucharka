package cz.sajvera.ppro.bean;

import cz.sajvera.ppro.dao.KategorieDao;
import cz.sajvera.ppro.dao.ReceptDao;
import cz.sajvera.ppro.model.Fotka;
import cz.sajvera.ppro.model.Kategorie;
import cz.sajvera.ppro.model.Recept;
import cz.sajvera.ppro.model.Surovina;
import cz.sajvera.ppro.utils.ImageUtils;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class NovyReceptBean implements Serializable {

    @Inject
    private ReceptDao receptDao;

    private Recept recept;

    @Inject
    private KategorieBean kategorieBean;

    private List<Kategorie> kategorieList;

    private Surovina surovina;

    @Inject
    private PrihlaseniOdhlaseniBean prihlaseniOdhlaseniBean;

    private UploadedFile file;

    @PostConstruct
    public void init() {
        recept = new Recept();
        kategorieList = kategorieBean.getKategorieList();
        surovina = new Surovina();
    }

    public String pridatRecept() {
        if(recept.getSuroviny().isEmpty()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nepřidány žádné suroviny!", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        } else {
            if(file.getSize() > 0) {
                recept.setFotka(ImageUtils.vytvorFotku(file));
            } else {
                recept.setFotka(new Fotka("/resources/img/teddybear.jpg", "/resources/img/teddybear.jpg", "/resources/img/teddybear.jpg"));
            }
            recept.setDatumPridani(new Date());
            recept.setUzivatel(prihlaseniOdhlaseniBean.getUzivatel());
            receptDao.save(recept);
            String zprava;
            if(recept.getNazev().length() > 15)
                zprava = "Recept " + recept.getNazev().substring(0, 14) + "... úspěšně přidán.";
            else
                zprava = "Recept " + recept.getNazev() + " úspěšně přidán.";
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, zprava, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "seznamreceptu?faces-redirect=true";
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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void reinitFile() {
        file = null;
    }

    public void validate(FacesContext context, UIComponent component, Object value) {
        UploadedFile file = (UploadedFile) value;
        if(file.getSize() > 0) {
            if (!(file.getContentType().indexOf("image/") >= 0)) {
                throw new ValidatorException(new FacesMessage("Soubor není typu obrázek!"));
            }
            if (file.getSize() > 10000000) {
                throw new ValidatorException(new FacesMessage("Soubor musí být měnší než 10 MB."));
            }
        }
    }

}