package app.controller;

import app.dao.TaskDao;
import app.model.Task;
import app.model.User;

import javax.servlet.RequestDispatcher;
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
        String action = request.getPathInfo();
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                switch (action) {
                    case "/new":
                        showNewForm(request, response);
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
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
                        dispatcher.forward(request, response);
                        break;
                }
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showTaskList(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        setAttributeList(request);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/task_list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/task_form.jsp");
        dispatcher.forward(request, response);
    }

    private void showAllPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setAttributeList(request);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/all_page.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Task existingTask = taskDAO.select(id);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/task_form.jsp");
        request.setAttribute("task", existingTask);
        dispatcher.forward(request, response);

    }

    private void showGetPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Task existingTask = taskDAO.select(id);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/task_get.jsp");
        request.setAttribute("task", existingTask);
        dispatcher.forward(request, response);

    }

    private void insertTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String title = request.getParameter("title");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();
        String description = request.getParameter("description");
        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        String goalId = request.getParameter("goal_id");
        Task newTask;
        if (!goalId.equals("")) {
            newTask = new Task(title, description, username, LocalDate.now(), isDone, Long.parseLong(goalId));
        } else {
            newTask = new Task(title, description, username, LocalDate.now(), isDone);
        }
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
