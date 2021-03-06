package com.epam.esm.repository;

public interface CertificateRepo<T> extends Repository<T>{
    void update(T t);
    Boolean existById(int id);
}
