package com.github.company.dao.model;

import com.github.company.dao.entity.Producer;

import java.util.List;

public interface ProducerDao extends CRUD<Long, Producer> {
    List<Producer> getAll();
}