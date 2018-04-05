package com.github.company.dao.model;

import com.github.company.dao.entity.Order;

import java.util.List;

public interface OrderDao extends CRUD<Long, Order>, Pagination<Order> {
    List<Order> getAll();

    List<Order> getUserOrders(long id);
}