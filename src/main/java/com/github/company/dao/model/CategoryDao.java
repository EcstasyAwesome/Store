package com.github.company.dao.model;

import com.github.company.dao.entity.Category;

import java.util.List;

public interface CategoryDao extends CRUD<Long, Category> {
    List<Category> getAll();
}