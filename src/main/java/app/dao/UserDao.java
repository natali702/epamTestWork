package app.dao;

import app.model.User;
import app.utils.JDBCUtils;

import java.sql.*;

public class UserDao {
    @SuppressWarnings("SqlNoDataSourceInspection")
    private static final String SELECT_USER_BY_NAME_PASSWORD = "SELECT * FROM users WHERE username = ? AND password = ?";

    public boolean validate(User user) throws ClassNotFoundException {
        boolean status = false;
        Class.forName("org.postgresql.Driver");
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_USER_BY_NAME_PASSWORD)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }
}
