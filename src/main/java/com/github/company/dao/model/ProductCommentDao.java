package com.github.company.dao.model;

import com.github.company.dao.entity.ProductComment;

public interface ProductCommentDao extends CRUD<Long, ProductComment>, Pagination<ProductComment> {
}