package cz.sajvera.ppro.converter;

import cz.sajvera.ppro.bean.SpravaUzivateluBean;
import cz.sajvera.ppro.model.Role;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "roleConverter")
public class RoleConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String roleID) {
        ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),"#{spravaUzivateluBean}", SpravaUzivateluBean.class);
        SpravaUzivateluBean sub = (SpravaUzivateluBean) vex.getValue(ctx.getELContext());
        return sub.getRole(Integer.valueOf(roleID));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object role) {
        return String.valueOf(((Role) role).getId());
    }

}
