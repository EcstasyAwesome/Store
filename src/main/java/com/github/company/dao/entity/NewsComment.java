package com.github.company.dao.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "news_comments")
public class NewsComment extends Comment {

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