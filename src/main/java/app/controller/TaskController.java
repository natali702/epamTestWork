package app.controller;

import app.dao.TaskDao;
import app.model.Task;
import app.model.User;
import app.utils.ControllerUtils;
import app.utils.JspPathUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/task/*")
public class TaskController extends HttpServlet {
    private TaskDao taskDAO;

    public void init() {
        taskDAO = new TaskDao();
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
                    case "/new":
                        ControllerUtils.showPage(request, response, JspPathUtils.TASK_FORM);
                        break;
                    case "/insert":
                        insertTask(request, response);
                        break;
                    case "/delete":
                        deleteTask(request, response);
                        break;
                    case "/edit":
                        showEditForm(request, response);
                        break;
                    case "/update":
                        updateTask(request, response);
                        break;
                    case "/list":
                        showTaskList(request, response);
                        break;
                    case "/all":
                        showAllPage(request, response);
                        break;
                    case "/get":
                        showGetPage(request, response);
                        break;
                    case "/logout":
                        request.getRequestDispatcher("/logout").forward(request, response);
                        break;
                    default:
                        ControllerUtils.showPage(request, response, JspPathUtils.LOGIN_PAGE);
                        break;
                }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showTaskList(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        setAttributeList(request);
        ControllerUtils.showPage(request, response, JspPathUtils.TASK_LIST);
    }

    private void showAllPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setAttributeList(request);
        ControllerUtils.showPage(request, response, JspPathUtils.ALL_PAGE);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Task existingTask = taskDAO.select(id);
        request.setAttribute("task", existingTask);
        ControllerUtils.showPage(request, response, JspPathUtils.TASK_FORM);
    }

    private void showGetPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Task existingTask = taskDAO.select(id);
        request.setAttribute("task", existingTask);
        ControllerUtils.showPage(request, response, JspPathUtils.TASK_GET);
    }

    private void insertTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String title = request.getParameter("title");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();
        String description = request.getParameter("description");
        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        String goal = request.getParameter("goal_id");
        long goalId = ControllerUtils.ParseStringToLong(goal);
        Task newTask = new Task(title, description, username, LocalDate.now(), isDone, goalId);
        taskDAO.insert(newTask);
        response.sendRedirect("list");
    }

    private void updateTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String title = request.getParameter("title");
        User user = (User) request.getSession(false).getAttribute("user");
        String username = user.getUsername();
        String description = request.getParameter("description");
        LocalDate taskDate = LocalDate.parse(request.getParameter("taskDate"));
        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        Task updateTask = new Task(id, title, description, username, taskDate, isDone);
        taskDAO.update(updateTask);
        response.sendRedirect("list");
    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        taskDAO.delete(id);
        response.sendRedirect("list");
    }

    private void setAttributeList(HttpServletRequest request) {
        User user = (User) request.getSession(false).getAttribute("user");
        String name = user.getUsername();
        List<Task> listTask = taskDAO.selectAllByUsername(name);
        request.setAttribute("listTask", listTask);
    }
}
