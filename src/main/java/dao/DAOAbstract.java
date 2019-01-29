package dao;

import java.util.List;

public interface DAOAbstract<T> {
    List<T> selectAll();
    T get(String id);
    boolean update(T object);
    boolean delete(T object);
}
