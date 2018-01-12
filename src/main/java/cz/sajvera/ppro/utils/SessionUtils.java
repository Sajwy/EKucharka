package cz.sajvera.ppro.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class SessionUtils {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static Map<String, Object> getSessionMap() {
        return  FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }

    public static String getUzivatelskeJmeno() {
        HttpSession session = getSession();
        if (session != null)
            return (String) session.getAttribute("jmeno").toString();
        else
            return null;
    }

    public static String getRole() {
        HttpSession session = getSession();
        if (session != null)
            return (String) session.getAttribute("role").toString();
        else
            return null;
    }

}
