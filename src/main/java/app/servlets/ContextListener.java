package app.servlets;

import app.model.Task;
import app.services.Authentication;
import app.services.AuthenticationService;
import app.utils.TaskUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ContextListener implements ServletContextListener {

    private Map<Integer, Task> tasks;
    private AtomicInteger idCounter;
    private Authentication authService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        tasks = new ConcurrentHashMap<>();
        idCounter = new AtomicInteger(4);
        authService = new AuthenticationService();
        servletContext.setAttribute("tasks", tasks);
        servletContext.setAttribute("idCounter", idCounter);
        servletContext.setAttribute("authService", authService);

        List<Task> taskList = TaskUtils.generateTasks();
        taskList.forEach(task -> this.tasks.put(task.getId(), task));

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //Close resource.
        tasks = null;
    }
}
