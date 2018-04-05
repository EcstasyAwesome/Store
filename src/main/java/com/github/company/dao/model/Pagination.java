package com.github.company.dao.model;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public interface Pagination<T> {
    List<T> getPage(int page, int recordsOnPage, @Nullable Map<String, String> params);

    int amountOfPages(int recordsOnPage, @Nullable Map<String, String> params);
}