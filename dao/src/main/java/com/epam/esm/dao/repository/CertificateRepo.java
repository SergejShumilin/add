package com.epam.esm.dao.repository;

import java.util.List;

public interface CertificateRepo<T> {
    void save(T t);
    void update(T t);
    void delete(int id);
    List<T> findAll();

    List<T> query(CertificateSqlSpecification specification);
}
