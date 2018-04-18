package com.github.company.controller;

import com.github.company.dao.model.CategoryDao;
import com.github.company.dao.model.NewsDao;
import com.github.company.dao.model.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class NewsController {

    private final NewsDao newsDao;
    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    @Autowired
    public NewsController(NewsDao newsDao, CategoryDao categoryDao, ProductDao productDao) {
        this.newsDao = newsDao;
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @GetMapping
    public String getNews(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        int recordsOnPage = 10;
        model.addAttribute("news", newsDao.getPage(page, recordsOnPage, null));
        model.addAttribute("categories", categoryDao.getAll());
        model.addAttribute("products", productDao.getMostRatedProducts());
        model.addAttribute("currentPage", page);
        model.addAttribute("noOfPages", newsDao.amountOfPages(recordsOnPage, null));
        return "news_all";
    }

    @GetMapping("/news/{id}")
    public String getNewsById(@PathVariable long id, Model model) {
        model.addAttribute("news", newsDao.read(id));
        model.addAttribute("categories", categoryDao.getAll());
        return "news_id";
    }
}
