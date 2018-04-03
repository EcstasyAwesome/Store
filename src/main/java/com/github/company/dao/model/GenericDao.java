package com.github.company.dao.model;

public interface GenericDao<T, E> {
    void create(E instance);

    E read(T id);

    void update(E instance);

    void delete(T id);
}