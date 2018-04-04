package com.github.company.dao.impl;

import com.github.company.dao.entity.Order;
import com.github.company.dao.model.OrderDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderDaoImpl implements OrderDao {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    @Override
    public List<Order> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        query.select(query.from(Order.class));
        return session.createQuery(query).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getUserOrders(long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        query.select(root).where(builder.equal(root.get("user_id"), id));
        return session.createQuery(query).getResultList();
    }

    @Override
    public void create(Order newInstance) {
        sessionFactory.getCurrentSession().save(newInstance);
    }

    @Transactional(readOnly = true)
    @Override
    public Order read(Long id) {
        return sessionFactory.getCurrentSession().get(Order.class, id);
    }

    @Override
    public void update(Order instance) {
        sessionFactory.getCurrentSession().update(instance);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(Order.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getPage(int page, int recordsOnPage, @Nullable String column, @Nullable String value) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        query.select(root);
        if (column != null && value != null) query.where(builder.equal(root.get(column), value));
        Query<Order> orderQuery = session.createQuery(query);
        orderQuery.setFirstResult(page * recordsOnPage - recordsOnPage);
        orderQuery.setMaxResults(recordsOnPage);
        return orderQuery.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public int amountOfPages(int recordsOnPage, @Nullable String column, @Nullable String value) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Order> root = query.from(Order.class);
        query.select(builder.count(root));
        if (column != null && value != null) query.where(builder.equal(root.get(column), value));
        long records = session.createQuery(query).getSingleResult();
        return (int) Math.ceil(records * 1.0 / recordsOnPage);
    }
}