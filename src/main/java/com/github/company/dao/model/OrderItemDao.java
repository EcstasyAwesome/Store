package com.github.company.dao.model;

import com.github.company.dao.entity.OrderItem;

import java.util.List;

public interface OrderItemDao extends CRUD<Long, OrderItem> {
    List<OrderItem> getItemsByOrder(long id);
}