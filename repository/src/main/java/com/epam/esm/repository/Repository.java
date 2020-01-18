package com.epam.esm.repository;

import com.epam.esm.repository.specification.SqlSpecification;

import java.util.List;

public interface Repository<T> {
    void save(T t);
    void delete(int id);

    List<T> query(SqlSpecification specification);
}
