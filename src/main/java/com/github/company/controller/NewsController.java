package com.github.company.controller;

import com.github.company.dao.entity.News;
import com.github.company.dao.entity.NewsComment;
import com.github.company.dao.entity.User;
import com.github.company.dao.model.CategoryDao;
import com.github.company.dao.model.NewsCommentDao;
import com.github.company.dao.model.NewsDao;
import com.github.company.dao.model.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class NewsController {

    private final NewsDao newsDao;
    private final CategoryDao categoryDao;
    private final ProductDao productDao;
    private final NewsCommentDao newsCommentDao;

    @Autowired
    public NewsController(NewsDao newsDao, CategoryDao categoryDao, ProductDao productDao, NewsCommentDao newsCommentDao) {
        this.newsDao = newsDao;
        this.categoryDao = categoryDao;
        this.productDao = productDao;
        this.newsCommentDao = newsCommentDao;
    }

    @ModelAttribute(value = "comment")
    public NewsComment newComment() {
        return new NewsComment();
    }

    @GetMapping
    public String getNews(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        int recordsOnPage = 15;
        model.addAttribute("news", newsDao.getPage(page, recordsOnPage, null));
        model.addAttribute("categories", categoryDao.getAll());
        model.addAttribute("products", productDao.getMostRatedProducts());
        model.addAttribute("currentPage", page);
        model.addAttribute("numberOfPages", newsDao.amountOfPages(recordsOnPage, null));
        return "news_all";
    }

    @GetMapping("/news/{id}")
    public String getNewsById(Model model,
                              @PathVariable long id,
                              @RequestParam(value = "page", defaultValue = "1") int page) {
        int recordsOnPage = 10;
        Map<String, Object> params = new HashMap<>();
        params.put("news", id);
        model.addAttribute("news", newsDao.read(id));
        model.addAttribute("categories", categoryDao.getAll());
        model.addAttribute("comments", newsCommentDao.getPage(page, recordsOnPage, params));
        model.addAttribute("currentPage", page);
        model.addAttribute("numberOfPages", newsCommentDao.amountOfPages(recordsOnPage, params));
        return "news_id";
    }

    @PostMapping("/news/{id}")
    public String addComment(Model model,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @PathVariable long id,
                             @Valid @ModelAttribute("comment") NewsComment newsComment,
                             BindingResult result) {
        if (result.hasErrors()) return getNewsById(model, id, page);
        else {
            newsComment.setNews(new News(id));
            newsComment.setUser(new User(1L)); // temporarily
            newsCommentDao.create(newsComment);
            model.addAttribute("comment", newComment());
            return getNewsById(model, id, page);
        }
    }
}