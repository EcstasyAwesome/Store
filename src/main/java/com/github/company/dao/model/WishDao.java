package com.github.company.dao.model;

import com.github.company.dao.entity.Wish;

import java.util.List;

public interface WishDao extends CRUD<Long, Wish> {
    List<Wish> getUserWishes(long id);
}