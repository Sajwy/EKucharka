package cz.sajvera.ppro.validator;

import cz.sajvera.ppro.dao.UzivatelDao;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class UzivatelskeJmenoValidator implements Validator {

    @Inject
    private UzivatelDao uzivatelDao;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        if(!uzivatelDao.jeUzivatelskeJmenoVolne((String) value)) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Uživatelské jméno " + ((String) value) + " je již použito!", "Zvolte si prosím jiné.");
            throw new ValidatorException(fmsg);
        }
    }

}
