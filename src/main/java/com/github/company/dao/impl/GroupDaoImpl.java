package com.github.company.dao.impl;

import com.github.company.dao.entity.Group;
import com.github.company.dao.model.GroupDao;
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
public class GroupDaoImpl implements GroupDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public GroupDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Group> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Group> query = builder.createQuery(Group.class);
        query.select(query.from(Group.class));
        return session.createQuery(query).getResultList();
    }

    @Override
    public Long create(Group newInstance) {
        return (Long) sessionFactory.getCurrentSession().save(newInstance);
    }

    @Transactional(readOnly = true)
    @Override
    public Group read(Long id) {
        return sessionFactory.getCurrentSession().get(Group.class, id);
    }

    @Override
    public void update(Group instance) {
        sessionFactory.getCurrentSession().update(instance);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(Group.class, id));
    }
}