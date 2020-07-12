package app.controller;

import app.utils.JspPathUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogOutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
        session.removeAttribute("password");
        session.removeAttribute("username");
        session.removeAttribute("user");
        req.getRequestDispatcher(JspPathUtils.LOGIN_PAGE).forward(req, resp);
    }
}
