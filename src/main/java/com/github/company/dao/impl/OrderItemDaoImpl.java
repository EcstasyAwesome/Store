package com.github.company.dao.impl;

import com.github.company.dao.entity.OrderItem;
import com.github.company.dao.model.OrderItemDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderItemDaoImpl implements OrderItemDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    @Override
    public List<OrderItem> getItemsByOrder(long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<OrderItem> query = builder.createQuery(OrderItem.class);
        Root<OrderItem> root = query.from(OrderItem.class);
        query.select(root).where(builder.equal(root.get("order"), id));
        return session.createQuery(query).getResultList();
    }

    @Override
    public void create(OrderItem newInstance) {
        sessionFactory.getCurrentSession().save(newInstance);
    }

    @Transactional(readOnly = true)
    @Override
    public OrderItem read(Long id) {
        return sessionFactory.getCurrentSession().get(OrderItem.class, id);
    }

    @Override
    public void update(OrderItem instance) {
        sessionFactory.getCurrentSession().update(instance);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(OrderItem.class, id));
    }
}