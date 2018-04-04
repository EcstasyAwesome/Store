package com.github.company.dao.model;

import com.github.company.dao.entity.ProductLine;

import java.util.List;

public interface ProductLineDao extends CRUD<Long, ProductLine> {
    List<ProductLine> getAll();
}