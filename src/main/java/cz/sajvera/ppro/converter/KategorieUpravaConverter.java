package cz.sajvera.ppro.converter;

import cz.sajvera.ppro.bean.UpravaReceptuBean;
import cz.sajvera.ppro.model.Kategorie;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "kategorieUpravaConverter")
public class KategorieUpravaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String kategorieID) {
        ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),"#{upravaReceptuBean}", UpravaReceptuBean.class);
        UpravaReceptuBean urb = (UpravaReceptuBean) vex.getValue(ctx.getELContext());
        return urb.getKategorie(Integer.valueOf(kategorieID));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object kategorie) {
        return String.valueOf(((Kategorie)kategorie).getId());
    }

}
