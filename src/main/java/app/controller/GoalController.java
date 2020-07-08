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
        System.out.println("doPost from GoalController");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("start doGet from GoalController");
        String action = request.getPathInfo();
        boolean hasImage = action.startsWith("/images");

        HttpSession session = request.getSession(false);
        try {
            if(session != null && !hasImage) {
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
//                    case "/logout":
//                        request.getRequestDispatcher("/logout").forward(request, response);
//                        break;
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
            throws SQLException, IOException, ServletException {
        System.out.println("start listGoal from GoalController");
        List<Goal> listGoal = goalDao.selectAll();
        request.setAttribute("listGoal", listGoal);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/goal_list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("start showNewForm from GoalController");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/goal_form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        System.out.println("start showEditForm from GoalController");
        int id = Integer.parseInt(request.getParameter("id"));
        Goal existingGoal = goalDao.select(id);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/goal_form.jsp");
        request.setAttribute("goal", existingGoal);
        dispatcher.forward(request, response);

    }

    private void insertGoal(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("start insertGoal from GoalController");
        String title = request.getParameter("title");
        long parentId = Long.valueOf(request.getParameter("parent"));
        Goal newGoal = new Goal(title, parentId);
        goalDao.insert(newGoal);
        response.sendRedirect("list");
    }

    private void updateGoal(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("start updateGoal from GoalController");
        long id = Long.parseLong(request.getParameter("id"));
        String title = request.getParameter("title");
        long parentId = Long.valueOf(request.getParameter("parent"));
        Goal updateGoal = new Goal(id, title, parentId);
        goalDao.update(updateGoal);
        response.sendRedirect("list");
    }

    private void deleteGoal(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("start deleteGoal from GoalController");
        long id = Long.parseLong(request.getParameter("id"));
        goalDao.delete(id);
        response.sendRedirect("list");
    }
}
