package com.github.company.dao.impl;

import com.github.company.dao.entity.News;
import com.github.company.dao.model.NewsDao;
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
public class NewsDaoImpl implements NewsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    @Override
    public List<News> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<News> query = builder.createQuery(News.class);
        query.select(query.from(News.class));
        return session.createQuery(query).getResultList();
    }

    @Override
    public void create(News newInstance) {
        sessionFactory.getCurrentSession().save(newInstance);
    }

    @Transactional(readOnly = true)
    @Override
    public News read(Long id) {
        return sessionFactory.getCurrentSession().get(News.class, id);
    }

    @Override
    public void update(News instance) {
        sessionFactory.getCurrentSession().update(instance);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(News.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<News> getPage(int page, int recordsOnPage, @Nullable Map<String, String> params) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<News> query = builder.createQuery(News.class);
        Root<News> root = query.from(News.class);
        query.select(root);
        if (params != null && !params.isEmpty())
            params.forEach((field, value) -> query.where(builder.equal(root.get(field), value)));
        Query<News> newsQuery = session.createQuery(query);
        newsQuery.setFirstResult(page * recordsOnPage - recordsOnPage);
        newsQuery.setMaxResults(recordsOnPage);
        return newsQuery.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public int amountOfPages(int recordsOnPage, @Nullable Map<String, String> params) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<News> root = query.from(News.class);
        query.select(builder.count(root));
        if (params != null && !params.isEmpty())
            params.forEach((field, value) -> query.where(builder.equal(root.get(field), value)));
        long records = session.createQuery(query).getSingleResult();
        return (int) Math.ceil(records * 1.0 / recordsOnPage);
    }
}