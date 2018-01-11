package cz.sajvera.ppro.bean;

import cz.sajvera.ppro.dao.UzivatelDao;
import cz.sajvera.ppro.model.Uzivatel;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

@Named
@ViewScoped
public class PrihlaseniBean implements Serializable {

    @Inject
    private UzivatelDao uzivatelDao;

    private Uzivatel uzivatel;

    @PostConstruct
    public void init() {
        uzivatel = new Uzivatel();
    }

    public String prihlasit() throws NoSuchAlgorithmException {
        if(uzivatelDao.overUzivatele(uzivatel.getUzivatelskeJmeno(), uzivatel.vytvorHashHesla(uzivatel.getHeslo()))) {
            return "index?faces-redirect=true";
        }
        RequestContext.getCurrentInstance().update("panel");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Přihlášení se nezdařilo", "Zadány špatné vstupní údaje!"));
        return "";
    }

    public Uzivatel getUzivatel() {
        return uzivatel;
    }

    public void setUzivatel(Uzivatel uzivatel) {
        this.uzivatel = uzivatel;
    }
}
