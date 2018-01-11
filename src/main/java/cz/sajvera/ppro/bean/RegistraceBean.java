package cz.sajvera.ppro.bean;

import cz.sajvera.ppro.dao.RoleDao;
import cz.sajvera.ppro.dao.UzivatelDao;
import cz.sajvera.ppro.model.Role;
import cz.sajvera.ppro.model.Uzivatel;

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
public class RegistraceBean implements Serializable {

    @Inject
    private UzivatelDao uzivatelDao;

    private Uzivatel uzivatel;

    @Inject
    private RoleDao roleDao;

    private Role role;

    @PostConstruct
    public void init() {
        uzivatel = new Uzivatel();
        role = roleDao.findRoleByNazev("Uživatel");
    }

    public String registrovat() throws NoSuchAlgorithmException {
        role.getUzivatele().add(uzivatel);
        uzivatel.setRole(role);
        uzivatel.setHeslo(uzivatel.vytvorHashHesla(uzivatel.getHeslo()));
        uzivatelDao.save(uzivatel);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Uživatel " + uzivatel.getUzivatelskeJmeno() + " úspěšně zaregistrován.", "Nyní se můžete přihlásit.");
        FacesContext.getCurrentInstance().addMessage(null, message);

        return "prihlaseni?faces-redirect=true";
    }

    public Uzivatel getUzivatel() {
        return uzivatel;
    }

    public void setUzivatel(Uzivatel uzivatel) {
        this.uzivatel = uzivatel;
    }
}
