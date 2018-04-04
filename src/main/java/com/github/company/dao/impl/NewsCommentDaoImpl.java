package com.github.company.dao.impl;

import com.github.company.dao.entity.NewsComment;
import com.github.company.dao.model.NewsCommentDao;
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
public class NewsCommentDaoImpl implements NewsCommentDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void create(NewsComment newInstance) {
        sessionFactory.getCurrentSession().save(newInstance);
    }

    @Transactional(readOnly = true)
    @Override
    public NewsComment read(Long id) {
        return sessionFactory.getCurrentSession().get(NewsComment.class, id);
    }

    @Override
    public void update(NewsComment instance) {
        sessionFactory.getCurrentSession().update(instance);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(NewsComment.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<NewsComment> getPage(int page, int recordsOnPage, @Nullable String column, @Nullable String value) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<NewsComment> query = builder.createQuery(NewsComment.class);
        Root<NewsComment> root = query.from(NewsComment.class);
        query.select(root);
        if (column != null && value != null) query.where(builder.equal(root.get(column), value));
        Query<NewsComment> newsCommentQuery = session.createQuery(query);
        newsCommentQuery.setFirstResult(page * recordsOnPage - recordsOnPage);
        newsCommentQuery.setMaxResults(recordsOnPage);
        return newsCommentQuery.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public int amountOfPages(int recordsOnPage, @Nullable String column, @Nullable String value) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<NewsComment> root = query.from(NewsComment.class);
        query.select(builder.count(root));
        if (column != null && value != null) query.where(builder.equal(root.get(column), value));
        long records = session.createQuery(query).getSingleResult();
        return (int) Math.ceil(records * 1.0 / recordsOnPage);
    }
}