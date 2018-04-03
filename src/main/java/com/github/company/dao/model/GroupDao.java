package com.github.company.dao.model;

import com.github.company.dao.entity.Group;

import java.util.List;

public interface GroupDao extends GenericDao<Long, Group> {
    List<Group> getAll();
}