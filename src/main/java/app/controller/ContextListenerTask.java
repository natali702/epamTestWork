package app.controller;

import app.services.Authentication;
import app.services.AuthenticationService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ContextListenerTask implements ServletContextListener {

    //private Authentication authService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("init from ContextListenerTask");

        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        servletContext.setAttribute("authService", new AuthenticationService());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //Close resource.
    }
}
