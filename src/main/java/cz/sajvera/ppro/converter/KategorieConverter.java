package cz.sajvera.ppro.converter;

import cz.sajvera.ppro.bean.NovyReceptBean;
import cz.sajvera.ppro.model.Kategorie;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "kategorieConverter")
public class KategorieConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String kategorieID) {
        ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),"#{novyReceptBean}", NovyReceptBean.class);
        NovyReceptBean kategorie = (NovyReceptBean) vex.getValue(ctx.getELContext());
        return kategorie.getKategorie(Integer.valueOf(kategorieID));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object kategorie) {
        return ((Kategorie)kategorie).getId()+"";
    }

}
