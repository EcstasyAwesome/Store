package com.github.company.dao.model;

import com.github.company.dao.entity.NewsComment;

public interface NewsCommentDao extends GenericDao<Long, NewsComment>, Pagination<NewsComment> {
}