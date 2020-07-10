package app.controller;

import app.dao.GoalDao;
import app.model.Goal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/goal/*")
public class GoalController extends HttpServlet {
    private GoalDao goalDao;

    public void init() {
        goalDao = new GoalDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                switch (action) {
                    case "/new":
                        showNewForm(request, response);
                        break;
                    case "/insert":
                        insertGoal(request, response);
                        break;
                    case "/delete":
                        deleteGoal(request, response);
                        break;
                    case "/edit":
                        showEditForm(request, response);
                        break;
                    case "/update":
                        updateGoal(request, response);
                        break;
                    case "/list":
                        listGoal(request, response);
                        break;
                    case "/logout":
                        request.getRequestDispatcher("/logout").forward(request, response);
                        break;
                    default:
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
                        dispatcher.forward(request, response);
                        break;
                }
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listGoal(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Goal> listGoal = goalDao.selectAll();
        request.setAttribute("listGoal", listGoal);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/goal_list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/goal_form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Goal existingGoal = goalDao.select(id);
        request.setAttribute("goal", existingGoal);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/goal_form.jsp");
        dispatcher.forward(request, response);

    }

    private void insertGoal(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String title = request.getParameter("title");
        String parent = request.getParameter("parent");
        Goal newGoal;
        if (!parent.equals("")) {
            newGoal = new Goal(title, Long.valueOf(parent));
        } else {
            newGoal = new Goal(title);
        }
        goalDao.insert(newGoal);
        response.sendRedirect("list");
    }

    private void updateGoal(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String title = request.getParameter("title");
        String parent = request.getParameter("parent");
        long parentId = Long.parseLong(parent);
        Goal updateGoal;
        if (parentId != 0) {
            updateGoal = new Goal(id, title, parentId);
        } else {
            updateGoal = new Goal(id, title);
        }
        goalDao.update(updateGoal);
        response.sendRedirect("list");
    }

    private void deleteGoal(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        goalDao.delete(id);
        response.sendRedirect("list");
    }
}
