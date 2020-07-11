package app.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    void insert(T t) throws SQLException;

    T select(long tId);

    List<T> selectAll();

    boolean delete(long id) throws SQLException;

    boolean update(T t) throws SQLException;

}
