package cz.sajvera.ppro.bean;

import cz.sajvera.ppro.dao.UzivatelDao;
import cz.sajvera.ppro.utils.CryptUtils;
import cz.sajvera.ppro.utils.SessionUtils;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
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

    private String jmeno;
    private String heslo;
    private boolean jePrihlasen;

    @PostConstruct
    public void init() {
        jePrihlasen = false;
    }

    public String prihlasit() throws NoSuchAlgorithmException {
        boolean shoda = uzivatelDao.overUzivatele(jmeno, CryptUtils.vytvorHashHesla(heslo));

        if(shoda) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("jmeno", jmeno);
            session.setAttribute("role", uzivatelDao.findUzivatelByUzivatelskeJmeno(jmeno).getRole().getNazev());
            jePrihlasen = true;
            return "index?faces-redirect=true";
        } else {
            RequestContext.getCurrentInstance().update("panel");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Přihlášení se nezdařilo", "Zadány špatné vstupní údaje!"));
            return "";
        }
    }

    public String odhlasit() {
        SessionUtils.getSessionMap().remove("jmeno");
        SessionUtils.getSessionMap().remove("role");
        SessionUtils.getSession().invalidate();
        setJePrihlasen(false);
        setHeslo(null);
        setJmeno(null);
        return "index.xhtml?faces-redirect=true";
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public boolean isJePrihlasen() {
        return jePrihlasen;
    }

    public void setJePrihlasen(boolean jePrihlasen) {
        this.jePrihlasen = jePrihlasen;
    }
}
