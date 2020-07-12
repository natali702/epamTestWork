package app.controller;

import app.dao.GoalDao;
import app.model.Goal;
import app.utils.ControllerUtils;
import app.utils.JspPathUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        if (!ControllerUtils.CheckUserSession(request, response)){
            return;
        }
        String action = request.getPathInfo();
        try {
            switch (action) {
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
                case "/new":
                    ControllerUtils.showPage(request,response,JspPathUtils.GOAL_FORM);
                    break;
                default:
                    ControllerUtils.showPage(request, response, JspPathUtils.LOGIN_PAGE);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listGoal(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Goal> listGoal = goalDao.selectAll();
        request.setAttribute("listGoal", listGoal);
        ControllerUtils.showPage(request, response, JspPathUtils.GOAL_LIST);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Goal existingGoal = goalDao.select(id);
        request.setAttribute("goal", existingGoal);
        ControllerUtils.showPage(request, response, JspPathUtils.GOAL_FORM);
    }

    private void insertGoal(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String title = request.getParameter("title");
        String parent = request.getParameter("parent");
        long parentId = ControllerUtils.ParseStringToLong(parent);
        Goal newGoal = new Goal(title, parentId);
        goalDao.insert(newGoal);
        response.sendRedirect("list");
    }

    private void updateGoal(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String title = request.getParameter("title");
        String parent = request.getParameter("parent");
        long parentId = ControllerUtils.ParseStringToLong(parent);
        Goal updateGoal = new Goal(id, title, parentId);
        goalDao.update(updateGoal);
        response.sendRedirect("list");
    }

    private void deleteGoal(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        goalDao.delete(id);
        response.sendRedirect("list");
    }
}
