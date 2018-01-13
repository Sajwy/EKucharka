package cz.sajvera.ppro.bean;

import cz.sajvera.ppro.dao.UzivatelDao;
import cz.sajvera.ppro.model.Uzivatel;
import cz.sajvera.ppro.utils.CryptUtils;
import cz.sajvera.ppro.utils.SessionUtils;

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
public class ZmenaUdajuBean implements Serializable {

    @Inject
    private UzivatelDao uzivatelDao;

    private Uzivatel uzivatel;

    @Inject
    private PrihlaseniOdhlaseniBean prihlaseniOdhlaseniBean;

    @PostConstruct
    public void init() {
        uzivatel = uzivatelDao.findUzivatelByUzivatelskeJmeno(SessionUtils.getUzivatelskeJmeno());
    }

    public Uzivatel getUzivatel() {
        return uzivatel;
    }

    public void setUzivatel(Uzivatel uzivatel) {
        this.uzivatel = uzivatel;
    }

    public String zmenUdaje() {
        uzivatelDao.merge(uzivatel);
        prihlaseniOdhlaseniBean.setUzivatel(uzivatel);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Údaje úspěšně změněny.", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "shrnuti?faces-redirect=true";
    }

    public String zmenHeslo() throws NoSuchAlgorithmException {
        uzivatel.setHeslo(CryptUtils.vytvorHashHesla(uzivatel.getHeslo()));
        uzivatelDao.merge(uzivatel);
        prihlaseniOdhlaseniBean.setUzivatel(uzivatel);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Heslo úspěšně změněno.", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "shrnuti?faces-redirect=true";
    }

}
