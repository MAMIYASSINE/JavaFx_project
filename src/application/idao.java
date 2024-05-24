package application;

import java.util.List;

public interface idao<T> {
    boolean create(T t);
    boolean delete(T t);
    boolean update(T t);
    T findById(int id);
    List<T> findAll();
}
