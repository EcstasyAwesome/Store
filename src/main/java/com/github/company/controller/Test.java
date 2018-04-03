package com.github.company.controller;

import com.github.company.dao.entity.Address;
import com.github.company.dao.entity.Group;
import com.github.company.dao.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/hello")
public class Test {

    @Autowired
    SessionFactory sessionFactory;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @ModelAttribute
    public User user() {
        User user = new User();
        user.setLastName("last");
        user.setFirstName("first");
        user.setMiddleName("middle");
        user.setBirthday(new Date());
        Address address = new Address();
        address.setCity("city");
        address.setCountry("country");
        address.setStreet("street");
        address.setHouse(15);
        user.setAddress(address);
        user.setPhone(380957476699L);
        user.setImage("image");
        user.setEmail("test1@gmail.com");
        user.setPassword("root123");
        Group group = new Group();
        group.setId(1L);
        user.setGroups(group);
        return user;
    }

    @GetMapping
    public String test() {
        return "test";
    }

    @Transactional
    @PostMapping
    public String saveUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.toString());
            return "test";
        }
        return "index";
    }
}