package com.github.company.dao.model;

import com.github.company.dao.entity.Product;

import java.util.List;

public interface ProductDao extends CRUD<Long, Product>, Pagination<Product> {
    List<Product> getAll();

    List<Product> getByCategory(long productLineId);

    List<Product> getMostRatedProducts();
}