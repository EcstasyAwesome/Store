package com.github.company.dao.model;

public interface CRUD<T, E> {
    void create(E newInstance);

    E read(T id);

    void update(E instance);

    void delete(T id);
}