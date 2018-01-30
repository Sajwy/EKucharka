package cz.sajvera.ppro.bean;

import cz.sajvera.ppro.dao.UzivatelDao;
import cz.sajvera.ppro.model.Uzivatel;
import cz.sajvera.ppro.utils.CryptUtils;
import cz.sajvera.ppro.utils.SessionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

@Named
@SessionScoped
public class PrihlaseniOdhlaseniBean implements Serializable {

    @Inject
    private UzivatelDao uzivatelDao;

    private boolean jePrihlasen;
    private Uzivatel uzivatel;

    @PostConstruct
    public void init() {
        jePrihlasen = false;
        uzivatel = new Uzivatel();
    }

    @PreDestroy
    public void predestroy() {
        jePrihlasen = false;
    }

    public String prihlasit() throws NoSuchAlgorithmException {
        boolean shoda = uzivatelDao.overUzivatele(uzivatel.getUzivatelskeJmeno(), CryptUtils.vytvorHashHesla(uzivatel.getHeslo()));

        if(shoda) {
            uzivatel = uzivatelDao.findUzivatelByUzivatelskeJmeno(uzivatel.getUzivatelskeJmeno());
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("jmeno", uzivatel.getUzivatelskeJmeno());
            session.setAttribute("role", uzivatel.getRole().getNazev());
            jePrihlasen = true;
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Uživatel " + uzivatel.getUzivatelskeJmeno() + " přihlášen.", "Vítejte na naší EKuchařce.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "index?faces-redirect=true";
        } else {
            uzivatel = new Uzivatel();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Přihlášení se nezdařilo", "Zadány špatné vstupní údaje!"));
            return null;
        }
    }

    public String odhlasit() {
        SessionUtils.getSessionMap().remove("jmeno");
        SessionUtils.getSessionMap().remove("role");
        SessionUtils.getSession().invalidate();
        setJePrihlasen(false);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Uživatel " + uzivatel.getUzivatelskeJmeno() + " odhlášen.", "Děkujeme za návštěvu.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        uzivatel = new Uzivatel();
        return "/index.xhtml?faces-redirect=true";
    }

    public String zrusitUcet() {
        uzivatelDao.delete(uzivatel);
        SessionUtils.getSessionMap().remove("jmeno");
        SessionUtils.getSessionMap().remove("role");
        SessionUtils.getSession().invalidate();
        setJePrihlasen(false);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Účet uživatele " + uzivatel.getUzivatelskeJmeno() + " zrušen.", "Nashledanoooooou.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        uzivatel = new Uzivatel();
        return "/index.xhtml?faces-redirect=true";
    }

    public Uzivatel getUzivatel() {
        return uzivatel;
    }

    public void setUzivatel(Uzivatel uzivatel) {
        this.uzivatel = uzivatel;
    }

    public boolean getJePrihlasen() {
        return jePrihlasen;
    }

    public void setJePrihlasen(boolean jePrihlasen) {
        this.jePrihlasen = jePrihlasen;
    }
}
