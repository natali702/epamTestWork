package app.dao;

import app.entitles.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                result = user;
            }
        }
        return result;
    }

    public boolean add(final User user) {
        for (User u : users) {
            if (u.getName().equals(user.getName()) && u.getPassword().equals(user.getPassword())) {
                return false;
            }
        }
        return users.add(user);
    }

    public boolean userIsExist(final String name, final String password) {
        boolean result = false;
        for (User user : users) {
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
