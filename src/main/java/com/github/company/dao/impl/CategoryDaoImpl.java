package com.github.company.dao.impl;

import com.github.company.dao.entity.Category;
import com.github.company.dao.model.CategoryDao;
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
public class CategoryDaoImpl implements CategoryDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public CategoryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        query.select(query.from(Category.class));
        return session.createQuery(query).getResultList();
    }

    @Override
    public Long create(Category newInstance) {
        return (Long) sessionFactory.getCurrentSession().save(newInstance);
    }

    @Transactional(readOnly = true)
    @Override
    public Category read(Long id) {
        return sessionFactory.getCurrentSession().get(Category.class, id);
    }

    @Override
    public void update(Category instance) {
        sessionFactory.getCurrentSession().update(instance);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(Category.class, id));
    }
}