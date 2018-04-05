package com.github.company.dao.impl;

import com.github.company.dao.entity.Product;
import com.github.company.dao.model.ProductDao;
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
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        query.select(query.from(Product.class));
        return session.createQuery(query).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getByProductLine(long productLineId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root).where(builder.equal(root.get("productLine"), productLineId));
        return session.createQuery(query).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getMostRatedProducts() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root).orderBy(builder.desc(root.get("rating")));
        Query<Product> productQuery = session.createQuery(query);
        productQuery.setFirstResult(0);
        productQuery.setMaxResults(5);
        return productQuery.getResultList();
    }

    @Override
    public void create(Product newInstance) {
        sessionFactory.getCurrentSession().save(newInstance);
    }

    @Transactional(readOnly = true)
    @Override
    public Product read(Long id) {
        return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Override
    public void update(Product instance) {
        sessionFactory.getCurrentSession().update(instance);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(Product.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getPage(int page, int recordsOnPage, @Nullable Map<String, String> params) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root);
        if (params != null && !params.isEmpty())
            params.forEach((field, value) -> query.where(builder.equal(root.get(field), value)));
        Query<Product> productQuery = session.createQuery(query);
        productQuery.setFirstResult(page * recordsOnPage - recordsOnPage);
        productQuery.setMaxResults(recordsOnPage);
        return productQuery.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public int amountOfPages(int recordsOnPage, @Nullable Map<String, String> params) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Product> root = query.from(Product.class);
        query.select(builder.count(root));
        if (params != null && !params.isEmpty())
            params.forEach((field, value) -> query.where(builder.equal(root.get(field), value)));
        long records = session.createQuery(query).getSingleResult();
        return (int) Math.ceil(records * 1.0 / recordsOnPage);
    }
}