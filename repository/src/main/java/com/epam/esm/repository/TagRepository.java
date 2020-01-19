package com.epam.esm.repository;

public interface TagRepository<T> extends Repository<T> {
    Boolean existById(int id);
    Boolean existByName(String name);
}
