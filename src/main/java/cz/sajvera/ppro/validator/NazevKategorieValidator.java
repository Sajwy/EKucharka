package cz.sajvera.ppro.validator;

import cz.sajvera.ppro.dao.KategorieDao;

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
public class NazevKategorieValidator implements Validator {

    @Inject
    private KategorieDao kategorieDao;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        if(!kategorieDao.lzeNazevPouzit((String) value)) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kategorie s názvem " + ((String) value) + " již existuje!", "Zvolte prosím jiný název.");
            throw new ValidatorException(fmsg);
        }
    }

}
