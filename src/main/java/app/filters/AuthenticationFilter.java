package app.filters;

import app.model.User;
import app.services.Authentication;
import app.utils.JspPathUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    private Authentication authService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String path = req.getRequestURI().substring(req.getContextPath().length());
        if (path.startsWith("/login") || path.startsWith("/images")) {
            filterChain.doFilter(req, res);
            return;
        }
        HttpSession session = req.getSession(false);
        boolean isAuth = false;
        if (session != null) {
            User sessionAttribute = (User) session.getAttribute("user");
            if (sessionAttribute != null) {
                isAuth = authService.isUserAuthenticated(sessionAttribute.getUsername(), sessionAttribute.getPassword());
            }
        }
        if (session == null || !isAuth) {
            req.getRequestDispatcher(JspPathUtils.LOGIN_PAGE).forward(req, res);
        } else {
            filterChain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
        this.authService = (Authentication) filterConfig.getServletContext().getAttribute("authService");
    }
}
