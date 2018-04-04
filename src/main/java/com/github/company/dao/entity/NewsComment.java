package com.github.company.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
        else {
            NewsComment that = (NewsComment) o;
            return news != null ? news.equals(that.news) : that.news == null;
        }
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (news != null ? news.hashCode() : 0);
        return result;
    }
}