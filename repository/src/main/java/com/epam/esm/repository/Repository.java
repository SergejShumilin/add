package com.epam.esm.repository;

import com.epam.esm.repository.specification.SqlSpecification;

import java.util.List;
import java.util.Set;

public interface Repository<T> {
    void save(T t);
    boolean delete(int id);

    List<T> query(SqlSpecification specification);
}
