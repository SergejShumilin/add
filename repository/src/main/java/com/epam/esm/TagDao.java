package com.epam.esm;

public interface TagDao<T> extends Dao<T> {
    T findByName(String name);
}
