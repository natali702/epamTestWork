package app.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
 //   Optional<T> get(long id);

  //  List<T> getAll();

   // void save(T t);

   // void update(T t, String[]params);

   // void delete(T t);
    //

    void insert(T t) throws SQLException;

    T select(long tId);

    List<T> selectAll();

    boolean delete(int id) throws SQLException;

    boolean update(T t) throws SQLException;

}
