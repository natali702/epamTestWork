package app.dao;

import app.model.Goal;
import app.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoalDao implements Dao {

    private static final String INSERT_GOAL_SQL = "INSERT INTO goals"
            + "  (title, parent) VALUES " + " (?, ?);";
    private static final String SELECT_GOAl_BY_ID = "select g_id, title, parent from goals where g_id =?";
    private static final String SELECT_ALL_GOALS = "select * from goals";
    //private static final String UPDATE_GOAL = "update goals set title = ?, parent =? where g_id = ?;";
    private static final String UPDATE_GOAL = "update goals set title = ? where g_id = ?;";
    private static final String DELETE_GOAL_BY_ID = "delete from goals where g_id = ?;";

    @Override
    public void insert(Object o) throws SQLException {
        Goal goal = (Goal) o;
        System.out.println(INSERT_GOAL_SQL);
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GOAL_SQL)) {
            preparedStatement.setString(1, goal.getTitle());
            preparedStatement.setLong(2, goal.getParentId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }

    @Override
    public Goal select(long goalId) {
        Goal goal = null;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GOAl_BY_ID);) {
            preparedStatement.setLong(1, goalId);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("g_id");
                String title = rs.getString("title");
                long parentId = rs.getLong("parent");
                if (parentId != 0) {
                    Goal parent = select(parentId);
                    goal = new Goal(id, title, parentId, parent);
                } else {
                    goal = new Goal(id, title, parentId);
                }
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return goal;
    }

    @Override
    public List<Goal> selectAll() {
        List<Goal> goals = new ArrayList<>();

        try (Connection connection = JDBCUtils.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GOALS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("g_id");
                String title = rs.getString("title");
                long parentId = rs.getLong("parent");
                if (parentId != 0) {
                    Goal parent = select(parentId);
                    goals.add(new Goal(id, title, parentId, parent));
                } else {
                    goals.add(new Goal(id, title, parentId));
                }
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return goals;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_GOAL_BY_ID);) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean update(Object o) throws SQLException {
        Goal goal = (Goal) o;
        boolean rowUpdated;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_GOAL);) {
            statement.setString(1, goal.getTitle());
            // statement.setLong(2, goal.getParentId());
            // statement.setLong(3, goal.getId());
            statement.setLong(2, goal.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
