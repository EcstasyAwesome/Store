package com.github.company.dao.impl;

import com.github.company.dao.entity.ProductLine;
import com.github.company.dao.model.ProductLineDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductLineDaoImpl implements ProductLineDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    @Override
    public List<ProductLine> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ProductLine> query = builder.createQuery(ProductLine.class);
        query.select(query.from(ProductLine.class));
        return session.createQuery(query).getResultList();
    }

    @Override
    public void create(ProductLine newInstance) {
        sessionFactory.getCurrentSession().save(newInstance);
    }

    @Transactional(readOnly = true)
    @Override
    public ProductLine read(Long id) {
        return sessionFactory.getCurrentSession().get(ProductLine.class, id);
    }

    @Override
    public void update(ProductLine instance) {
        sessionFactory.getCurrentSession().update(instance);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(ProductLine.class, id));
    }
}