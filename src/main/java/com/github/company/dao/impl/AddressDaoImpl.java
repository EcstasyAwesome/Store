package com.github.company.dao.impl;

import com.github.company.dao.entity.Address;
import com.github.company.dao.model.AddressDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AddressDaoImpl implements AddressDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Address instance) {
        sessionFactory.getCurrentSession().save(instance);
    }

    @Transactional(readOnly = true)
    @Override
    public Address read(Long id) {
        return sessionFactory.getCurrentSession().get(Address.class, id);
    }

    @Override
    public void update(Address instance) {
        sessionFactory.getCurrentSession().update(instance);
    }

    @Override
    public void delete(Long id) {
        Address address = sessionFactory.getCurrentSession().load(Address.class,id);
        sessionFactory.getCurrentSession().delete(address);
    }
}