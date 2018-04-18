package com.github.company.dao.impl;

import com.github.company.dao.entity.User;
import com.github.company.dao.model.UserDao;
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
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        query.select(query.from(User.class));
        return session.createQuery(query).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public User getByLogin(String email) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get("email"), email));
        return session.createQuery(query).getSingleResult();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean checkLoginAvailable(String email) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get("email"), email));
        return session.createQuery(query).getResultList().isEmpty();
    }

    @Override
    public Long create(User newInstance) {
        return (Long) sessionFactory.getCurrentSession().save(newInstance);
    }

    @Transactional(readOnly = true)
    @Override
    public User read(Long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public void update(User instance) {
        sessionFactory.getCurrentSession().update(instance);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(User.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getPage(int page, int recordsOnPage, @Nullable Map<String, String> params) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        if (params != null && !params.isEmpty())
            params.forEach((field, value) -> query.where(builder.equal(root.get(field), value)));
        Query<User> userQuery = session.createQuery(query);
        userQuery.setFirstResult(page * recordsOnPage - recordsOnPage);
        userQuery.setMaxResults(recordsOnPage);
        return userQuery.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public int amountOfPages(int recordsOnPage, @Nullable Map<String, String> params) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<User> root = query.from(User.class);
        query.select(builder.count(root));
        if (params != null && !params.isEmpty())
            params.forEach((field, value) -> query.where(builder.equal(root.get(field), value)));
        long records = session.createQuery(query).getSingleResult();
        return (int) Math.ceil(records * 1.0 / recordsOnPage);
    }
}