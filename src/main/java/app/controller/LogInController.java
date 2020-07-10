package app.controller;

import app.dao.UserDao;
import app.model.User;
import app.services.Authentication;

import javax.servlet.RequestDispatcher;
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
        System.out.println("init LogInController");
        userDao = new UserDao();
        this.authService = (Authentication) getServletContext().getAttribute("authService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet LogInController");
            resp.sendRedirect("WEB-INF/views/login.jsp"); //change index.html
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost LogInController");
        authenticate(req, resp);
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("start authenticate LogInController");
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

               RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/task_list.jsp");
               // RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/main_page.jsp");
                dispatcher.forward(request, response);
            } else {

                //session.setAttribute("user", username);
                //response.sendRedirect("login.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
