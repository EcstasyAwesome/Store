package com.github.company.dao.model;

import com.github.company.dao.entity.User;

import java.util.List;

public interface UserDao extends CRUD<Long, User>, Pagination<User> {
    List<User> getAll();

    User getByLogin(String login);

    boolean checkLoginAvailable(String login);
}