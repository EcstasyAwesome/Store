package com.github.company.dao.model;

import com.github.company.dao.entity.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Long, Product>, Pagination<Product> {
    List<Product> getAll();
    List<Product> getByProductLine(long productLineId);
    List<Product> getMostRatedProducts();
}