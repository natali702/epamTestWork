package app.dao;

import app.model.Goal;
import app.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Annotation @SuppressWarnings("ALL") used for region SQL_COMMANDS
 */
@SuppressWarnings("ALL")
public class GoalDao implements Dao {

    //region SQL_COMMANDS
    private static final String INSERT_SUBGOAL_SQL = "INSERT INTO goals"
            + "  (title, parent) VALUES " + " (?, ?);";

    private static final String INSERT_GOAL_SQL = "INSERT INTO goals"
            + "  (title) VALUES " + " (?);";

    private static final String SELECT_GOAl_BY_ID = "SELECT g_id, title, parent FROM goals WHERE g_id =?";
    private static final String SELECT_ALL_GOALS = "SELECT * FROM goals";
    private static final String UPDATE_GOAL = "UPDATE goals SET title = ? WHERE g_id = ?;";
    private static final String DELETE_GOAL_BY_ID = "DELETE FROM goals WHERE g_id = ?;";
    // endregion

    @Override
    public void insert(Object o) throws SQLException {
        Goal goal = (Goal) o;
        try (Connection connection = JDBCUtils.getConnection()) {
            PreparedStatement statement;
            if (goal.getParentId() != 0) {
                statement = connection.prepareStatement(INSERT_SUBGOAL_SQL);
                statement.setLong(2, goal.getParentId());
            } else {
                statement = connection.prepareStatement(INSERT_GOAL_SQL);
            }
            statement.setString(1, goal.getTitle());
            statement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
            throw exception;
        }
    }

    @Override
    public Goal select(long goalId) {
        Goal goal = null;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GOAl_BY_ID)) {
            preparedStatement.setLong(1, goalId);
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
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GOALS)) {
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
             PreparedStatement statement = connection.prepareStatement(UPDATE_GOAL)) {
            statement.setString(1, goal.getTitle());
            statement.setLong(2, goal.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
