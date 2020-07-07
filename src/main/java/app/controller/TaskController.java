package app.controller;

import app.dao.TaskDao;
import app.model.Task;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
@WebServlet("/")
public class TaskController extends HttpServlet {
    private TaskDao taskDAO;

    public void init() {
        taskDAO = new TaskDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doPost from TaskController");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("start doGet from TaskController");
        String action = request.getServletPath();
        boolean hasImage = action.startsWith("/images");

        HttpSession session = request.getSession(false);
        try {
            if(session != null && !hasImage) {
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
                        listTask(request, response);
                        break;
                    case "/logout":
                        request.getRequestDispatcher("/logout").forward(request, response);
                        break;
                    default:
                        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/login.jsp");
                        dispatcher.forward(request, response);
                        break;
                }
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listTask(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("start listTask from TaskController");
        List<Task> listTask = taskDAO.selectAll();
        request.setAttribute("listTask", listTask);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/task_list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("start showNewForm from TaskController");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/task_form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        System.out.println("start showEditForm from TaskController");
        int id = Integer.parseInt(request.getParameter("id"));
        Task existingTask = taskDAO.select(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/task_form.jsp");
        request.setAttribute("task", existingTask);
        dispatcher.forward(request, response);

    }

    private void insertTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("start insertTask from TaskController");
        String title = request.getParameter("title");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
//        String username = request.getParameter("username");
        String username = user.getUsername();
        String description = request.getParameter("description");

		/*DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"),df);*/

        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        Task newTask = new Task(title, description, username, LocalDate.now(), isDone);
        taskDAO.insert(newTask);
        response.sendRedirect("list");
    }

    private void updateTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("start updateTask from TaskController");
        int id = Integer.parseInt(request.getParameter("id"));

        String title = request.getParameter("title");
       // String username = request.getParameter("username");
        User user = (User) request.getSession(false).getAttribute("user");
        String username = user.getUsername();
        String description = request.getParameter("description");
        //DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDate taskDate = LocalDate.parse(request.getParameter("taskDate"));

        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        Task updateTask = new Task((long)id, title,description, username,  taskDate, isDone);

        taskDAO.update(updateTask);

        response.sendRedirect("list");
    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("start deleteTask from updateTask");
        int id = Integer.parseInt(request.getParameter("id"));
        taskDAO.delete(id);
        response.sendRedirect("list");
    }
}
