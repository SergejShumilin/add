package com.epam.esm.repository;

public interface TagRepository<T> extends Repository<T> {
    Boolean isExistById(int id);
    Boolean isExistByName(String name);
}
