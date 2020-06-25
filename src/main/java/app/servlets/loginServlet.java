package app.servlets;

import app.services.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class loginServlet extends HttpServlet {
    private Authentication authService;

    @Override
    public void init() throws ServletException {
        this.authService = (Authentication) getServletContext().getAttribute("authService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        if(authService.isUserAuthentication(userName,password)){
            req.getSession();
            resp.sendRedirect("/menu");
        } else {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("wrong login or password");
        }
    }
}
