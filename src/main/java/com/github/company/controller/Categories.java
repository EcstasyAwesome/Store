package com.github.company.controller;

import com.github.company.dao.entity.Category;
import com.github.company.dao.entity.Product;
import com.github.company.dao.model.CategoryDao;
import com.github.company.dao.model.ProducerDao;
import com.github.company.dao.model.ProductDao;
import com.github.company.util.template.FileService;
import com.github.company.util.template.Folder;
import com.github.company.validator.ImageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/category")
public class Categories {

    private final CategoryDao categoryDao;
    private final ProductDao productDao;
    private final ProducerDao producerDao;
    private final FileService fileService;
    private final ImageValidator imageValidator;

    @Autowired
    public Categories(CategoryDao categoryDao, ProductDao productDao,
                      ProducerDao producerDao, FileService fileService, ImageValidator imageValidator) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
        this.producerDao = producerDao;
        this.fileService = fileService;
        this.imageValidator = imageValidator;
    }

    @ModelAttribute("category")
    public Category newCategory() {
        return new Category();
    }

    @ModelAttribute("product")
    public Product newProduct() {
        return new Product();
    }

    @GetMapping
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryDao.getAll());
        return "category_all";
    }

    @GetMapping("{id}")
    public String getCategory(Model model,
                              @PathVariable long id,
                              @RequestParam(value = "page", defaultValue = "1") int page) {
        getCategories(model);
        int recordsOnPage = 12;
        Map<String, Object> params = new HashMap<>();
        params.put("category", id);
        model.addAttribute("category", categoryDao.read(id));
        model.addAttribute("products", productDao.getPage(page, recordsOnPage, params));
        model.addAttribute("currentPage", page);
        model.addAttribute("numberOfPages", productDao.amountOfPages(recordsOnPage, params));
        return "category_id";
    }

    @GetMapping("/add")
    public String addCategoryView(Model model) {
        getCategories(model);
        return "category_add";
    }

    @PostMapping("/add")
    public String addCategory(Model model,
                              @RequestParam("file") MultipartFile file,
                              @Valid @ModelAttribute("category") Category category,
                              BindingResult result) {
        if (imageValidator.supports(MultipartFile.class)) imageValidator.validate(file, result);
        if (result.hasErrors()) return addCategoryView(model);
        else {
            category.setImage(fileService.upload(file, Folder.IMAGE));
            categoryDao.create(category);
            model.addAttribute("category", newCategory());
            return getCategories(model);
        }
    }

    @GetMapping("{id}/add")
    public String addProductView(Model model, @PathVariable long id) {
        getCategories(model);
//        model.addAttribute("producers", producerDao.getAll());
        return "product_add";
    }

    @PostMapping("{id}/add")
    public String addProduct(Model model,
                             @PathVariable long id,
                             @RequestParam("file") MultipartFile file,
//                             @RequestParam("producer") long producerId,
                             @Valid @ModelAttribute("product") Product product,
                             BindingResult result) {
        if (imageValidator.supports(MultipartFile.class)) imageValidator.validate(file, result);
        if (result.hasErrors()) return addProductView(model, id);
        else {
            product.setImage(fileService.upload(file, Folder.IMAGE));
            product.setCategory(new Category(id));
//            product.setProducer(new Producer(producerId));
            model.addAttribute("product", newProduct());
            return String.format("redirect:/category/%d/product/%d", id, productDao.create(product));
        }
    }
}