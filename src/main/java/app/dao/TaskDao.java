package app.dao;

import app.model.Goal;
import app.model.Task;
import app.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDao implements Dao {
    private static final String INSERT_TASK_SQL = "INSERT INTO tasks"
            + "  (title, description, username, task_date,  is_done) VALUES " + " (?, ?, ?, ?, ?);";
//    private static final String SELECT_TASK_BY_ID = "select t_id,title,description,username,task_date,is_done,goal_id "
//            + "from tasks where t_id =?";
    private static final String SELECT_TASK_BY_ID = "SELECT t_id,tasks.title,description,username,task_date,is_done," +
            "goal_id,g_id, goals.title AS g_title, parent FROM tasks LEFT JOIN goals ON tasks.goal_id = goals.g_id " +
            "WHERE   t_id =?";
//    private static final String SELECT_ALL_TASKS = "select * from tasks";
private static final String SELECT_ALL_TASKS = "SELECT t_id,tasks.title,description,username,task_date,is_done,"
        + "goal_id,g_id, goals.title AS g_title, parent FROM tasks LEFT JOIN goals ON tasks.goal_id = goals.g_id";
    private static final String SELECT_ALL_TASKS_BY_USERNAME = "SELECT t_id,tasks.title,description,username,task_date,is_done,"
            + "goal_id,g_id, goals.title AS g_title, parent FROM tasks LEFT JOIN goals ON tasks.goal_id = goals.g_id " +
            "WHERE username = ?";
    private static final String UPDATE_TASK = "update tasks set title = ?, description =?, username= ?, task_date =?,"
            + " is_done = ? where t_id = ?;";
    private static final String DELETE_TASK_BY_ID = "delete from tasks where t_id = ?;";

    public TaskDao() {
    }

    @Override
    public void insert(Object o) throws SQLException {
        Task task = (Task) o;
        System.out.println(INSERT_TASK_SQL);
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASK_SQL)) {
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setString(3, task.getUsername());
            preparedStatement.setDate(4, JDBCUtils.getSQLDate(task.getTaskDate()));
            preparedStatement.setBoolean(5, task.getStatus());
            System.out.println(preparedStatement);
            //executeUpdate: выполняет такие команды, как INSERT, UPDATE, DELETE, CREATE TABLE, DROP TABLE.
            // В качестве результата возвращает количество строк, затронутых операцией
            // (например, количество добавленных, измененных или удаленных строк), или 0,
            // если ни одна строка не затронута операцией или если команда не изменяет содержимое таблицы
            // (например, команда создания новой таблицы)
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }

    @Override
    public boolean update(Object o) throws SQLException {
        Task task = (Task) o;
        boolean rowUpdated;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TASK);) {
            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setString(3, task.getUsername());
            statement.setDate(4, JDBCUtils.getSQLDate(task.getTaskDate()));
            statement.setBoolean(5, task.getStatus());
            statement.setLong(6, task.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public Task select(long taskId) {
        Task task = null;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TASK_BY_ID);) {
            preparedStatement.setLong(1, taskId);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("t_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String username = rs.getString("username");
                LocalDate taskDate = rs.getDate("task_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                long goalId =  rs.getLong("goal_id");
                if(goalId != 0){
                    Goal goal = new Goal(rs.getLong("g_id"), rs.getString("g_title"),
                            rs.getLong("parent"));
                    task = new Task(id, title, description,username,  taskDate, isDone, goalId, goal);
                }else {
                    task = new Task(id, title, description,username,  taskDate, isDone, goalId);
                }
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return task;
    }

    @Override
    public List<Task> selectAll() {

        List<Task> tasks = new ArrayList<>();

        try (Connection connection = JDBCUtils.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TASKS);) {
            System.out.println(preparedStatement);
            //выполняет команду SELECT. Возвращает объект ResultSet, который содержит результаты запроса
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("t_id");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String description = rs.getString("description");
                LocalDate taskDate = rs.getDate("task_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                //tasks.add(new Task(id, title, description, username, targetDate, isDone));
                long goalId =  rs.getLong("goal_id");
                if(goalId != 0){
                    Goal goal = new Goal(rs.getLong("g_id"), rs.getString("g_title"),
                            rs.getLong("parent"));
                    tasks.add(new Task(id, title, description,username,  taskDate, isDone, goalId, goal));
                }else {
                    tasks.add(new Task(id, title, description,username,  taskDate, isDone, goalId));
                }
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return tasks;
    }

    public List<Task> selectAllTasksByUsername(String name) {

        List<Task> tasks = new ArrayList<>();

        try (Connection connection = JDBCUtils.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TASKS_BY_USERNAME);) {
            preparedStatement.setString(1, name);
            System.out.println(preparedStatement);
            //выполняет команду SELECT. Возвращает объект ResultSet, который содержит результаты запроса
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("t_id");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String description = rs.getString("description");
                LocalDate taskDate = rs.getDate("task_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                //tasks.add(new Task(id, title, description, username, targetDate, isDone));
                long goalId =  rs.getLong("goal_id");
                if(goalId != 0){
                    Goal goal = new Goal(rs.getLong("g_id"), rs.getString("g_title"),
                            rs.getLong("parent"));
                    tasks.add(new Task(id, title, description,username,  taskDate, isDone, goalId, goal));
                }else {
                    tasks.add(new Task(id, title, description,username,  taskDate, isDone, goalId));
                }
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return tasks;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TASK_BY_ID);) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}
