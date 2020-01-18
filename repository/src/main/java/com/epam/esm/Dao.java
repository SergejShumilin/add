package com.epam.esm;



import java.util.List;

public interface Dao<T> {
    void save(T t);
    void delete(int id);
    List<T> findAll();
    T findById(int id);

    boolean isExistById(int id);
    boolean isExistByName(String name);
   }
