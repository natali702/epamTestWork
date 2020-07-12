package app.controller;

import app.dao.UserDao;
import app.model.User;
import app.services.Authentication;
import app.utils.ControllerUtils;
import app.utils.JspPathUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LogInController extends HttpServlet {

    private Authentication authService;
    private UserDao userDao;

    public void init() {
        this.userDao = new UserDao();
        this.authService = (Authentication) getServletContext().getAttribute("authService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(JspPathUtils.LOGIN_PAGE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authenticate(req, resp);
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        try {
            if (userDao.validate(user)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                authService.setUserData(username, password);
                ControllerUtils.showPage(request, response, JspPathUtils.TASK_LIST);
            } else {
                ControllerUtils.showPage(request, response, JspPathUtils.LOGIN_PAGE);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException();
        }
    }
}
