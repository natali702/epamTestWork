package app.dao;

import app.model.User;
import app.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final List<User> users = new ArrayList<>();

    public User getUserById(int id) {
        User result = new User();
        result.setId(-1);

        for (User user : users) {
            if (user.getId() == id) {
                result = user;
            }
        }
        return result;
    }

    public User getUserByloginPassword(final String name, final String password) {
        User result = new User();
        result.setId(-1);

        for (User user : users) {
            if (user.getUsername().equals(name) && user.getPassword().equals(password)) {
                result = user;
            }
        }
        return result;
    }

    public boolean add(final User user) {
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                return false;
            }
        }
        return users.add(user);
    }

    public boolean userIsExist(final String name, final String password) {
        boolean result = false;
        for (User user : users) {
            if (user.getUsername().equals(name) && user.getPassword().equals(password)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean validate(User user) throws ClassNotFoundException {

        boolean status = false;

        Class.forName("org.postgresql.Driver");

        try (Connection connection = JDBCUtils.getConnection();
             //PreparedStatement: предварительно компилирует запросы,
             //которые могут содержать входные параметры
             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from users where username = ? and password = ? ")) {
            //Устанавливаем в нужную позицию значения определённого типа
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            System.out.println(preparedStatement);
            //выполняем запрос
            ResultSet rs = preparedStatement.executeQuery();
            //rs это указатель на первую строку с выборки
            //чтобы вывести данные мы будем использовать
            //метод next() , с помощью которого переходим к следующему элементу
            status = rs.next();

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }
}
