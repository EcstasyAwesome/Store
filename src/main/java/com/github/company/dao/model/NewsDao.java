package com.github.company.dao.model;

import com.github.company.dao.entity.News;

import java.util.List;

public interface NewsDao extends GenericDao<Long, News>, Pagination<News> {
    List<News> getAll();
}