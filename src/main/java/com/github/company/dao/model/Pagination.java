package com.github.company.dao.model;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Pagination<T> {
    List<T> getPage(int page, int recordsOnPage, @Nullable String column, @Nullable String value);

    int amountOfPages(int recordsOnPage, @Nullable String column, @Nullable String value);
}