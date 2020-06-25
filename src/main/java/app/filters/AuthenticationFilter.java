package app.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//@WebFilter(urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse res = (HttpServletResponse) servletResponse;
//
//        String path = req.getRequestURI().substring(req.getContextPath().length());
//        if(path.startsWith("/login") || path.startsWith("/images")){
//            filterChain.doFilter(req,res);
//            return;
//        }
//        HttpSession session = req.getSession(false);
//        if(session == null){
//            req.getRequestDispatcher("/index.html").forward(req,res);
//        } else {
//            filterChain.doFilter(req,res);
//        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
