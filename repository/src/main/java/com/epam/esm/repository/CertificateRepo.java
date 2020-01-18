package com.epam.esm.repository;

import java.util.List;

public interface CertificateRepo<T> {
    void save(T t);
    void update(T t);
    void delete(int id);

    List<T> query(SqlSpecification specification);
}
