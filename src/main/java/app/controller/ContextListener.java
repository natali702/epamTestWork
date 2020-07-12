package app.controller;

import app.services.AuthenticationService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute("authService", new AuthenticationService());
    }
}
