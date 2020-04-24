package ro.siit.login;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/entity"})
public class LoginFilter implements Filter {
    public void init(FilterConfig arg0) throws ServletException{}
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Object authenticated =  httpServletRequest.getSession().getAttribute("authenticated");
        if (authenticated != null){
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect(request.getServletContext().getContextPath() + "/login");
        }

    }
}
