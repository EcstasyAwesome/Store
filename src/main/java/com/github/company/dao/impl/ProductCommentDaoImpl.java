package com.github.company.dao.impl;

import com.github.company.dao.entity.ProductComment;
import com.github.company.dao.model.ProductCommentDao;
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
public class ProductCommentDaoImpl implements ProductCommentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(ProductComment newInstance) {
        sessionFactory.getCurrentSession().save(newInstance);
    }

    @Transactional(readOnly = true)
    @Override
    public ProductComment read(Long id) {
        return sessionFactory.getCurrentSession().get(ProductComment.class, id);
    }

    @Override
    public void update(ProductComment instance) {
        sessionFactory.getCurrentSession().update(instance);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(ProductComment.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductComment> getPage(int page, int recordsOnPage, @Nullable Map<String, String> params) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ProductComment> query = builder.createQuery(ProductComment.class);
        Root<ProductComment> root = query.from(ProductComment.class);
        query.select(root);
        if (params != null && !params.isEmpty())
            params.forEach((field, value) -> query.where(builder.equal(root.get(field), value)));
        Query<ProductComment> productCommentQuery = session.createQuery(query);
        productCommentQuery.setFirstResult(page * recordsOnPage - recordsOnPage);
        productCommentQuery.setMaxResults(recordsOnPage);
        return productCommentQuery.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public int amountOfPages(int recordsOnPage, @Nullable Map<String, String> params) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<ProductComment> root = query.from(ProductComment.class);
        query.select(builder.count(root));
        if (params != null && !params.isEmpty())
            params.forEach((field, value) -> query.where(builder.equal(root.get(field), value)));
        long records = session.createQuery(query).getSingleResult();
        return (int) Math.ceil(records * 1.0 / recordsOnPage);
    }
}