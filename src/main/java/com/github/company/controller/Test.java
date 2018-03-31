package com.github.company.controller;

import com.github.company.dao.User;
import com.github.company.dao.UserValid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hello")
public class Test {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new UserValid());
    }

    @GetMapping
    public String test(Model model) {
        User user = new User();
        user.setId(2);
        user.setFirstName("Имя");
        user.setLastName("Фамилия");
        user.setAdmin(true);
        user.setInterests(new String[]{"a", "b", "c"});
        model.addAttribute("user", user);
        return "test";
    }

    @PostMapping
    public String saveUser(@Validated User user, BindingResult result) {
        if (result.hasErrors()) {
            return "test";
        }
        return "index";
    }
}