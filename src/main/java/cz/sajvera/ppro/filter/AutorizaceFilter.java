package cz.sajvera.ppro.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AutFilter", urlPatterns = { "*.xhtml" })
public class AutorizaceFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);
            String reqURI = reqt.getRequestURI();

            if(reqURI.indexOf("/error.xhtml") >= 0 || reqURI.indexOf("/erroropravneni.xhtml") >= 0) {
                resp.sendRedirect(reqt.getContextPath() + "/index.xhtml");
            } else if((reqURI.indexOf("/uzivatel/") >= 0 || reqURI.indexOf("/admin/") >= 0) && (ses == null || ses.getAttribute("jmeno") == null )) {
                resp.sendRedirect(reqt.getContextPath() + "/prihlaseni.xhtml");
            } else if(reqURI.indexOf("/admin/") >= 0 && ses != null && !ses.getAttribute("role").equals("Admin")) {
                reqt.getRequestDispatcher("/erroropravneni.xhtml").forward(reqt, resp);
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }

}
