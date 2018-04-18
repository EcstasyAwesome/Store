package com.github.company.dao.impl;

import com.github.company.dao.entity.Producer;
import com.github.company.dao.model.ProducerDao;
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
public class ProducerDaoImpl implements ProducerDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProducerDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Producer> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Producer> query = builder.createQuery(Producer.class);
        query.select(query.from(Producer.class));
        return session.createQuery(query).getResultList();
    }

    @Override
    public void create(Producer newInstance) {
        sessionFactory.getCurrentSession().save(newInstance);
    }

    @Transactional(readOnly = true)
    @Override
    public Producer read(Long id) {
        return sessionFactory.getCurrentSession().get(Producer.class, id);
    }

    @Override
    public void update(Producer instance) {
        sessionFactory.getCurrentSession().update(instance);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(Producer.class, id));
    }
}