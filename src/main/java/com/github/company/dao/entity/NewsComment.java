package com.github.company.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "newsComments")
public class NewsComment extends Comment {

    @NotNull
    private News news;

    @ManyToOne(optional = false)
    @JoinColumn
    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public NewsComment() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NewsComment that = (NewsComment) o;
        return Objects.equals(news, that.news);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), news);
    }

    @Override
    public String toString() {
        return "NewsComment{" +
                "news=" + news +
                "} " + super.toString();
    }
}