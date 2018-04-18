package com.github.company.dao.impl;

import com.github.company.dao.entity.Wish;
import com.github.company.dao.model.WishDao;
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
public class WishDaoImpl implements WishDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public WishDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Wish> getUserWishes(long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Wish> query = builder.createQuery(Wish.class);
        Root<Wish> root = query.from(Wish.class);
        query.select(root).where(builder.equal(root.get("user"), id));
        return session.createQuery(query).getResultList();
    }

    @Override
    public Long create(Wish newInstance) {
        return (Long) sessionFactory.getCurrentSession().save(newInstance);
    }

    @Transactional(readOnly = true)
    @Override
    public Wish read(Long id) {
        return sessionFactory.getCurrentSession().get(Wish.class, id);
    }

    @Override
    public void update(Wish instance) {
        sessionFactory.getCurrentSession().update(instance);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(Wish.class, id));
    }
}