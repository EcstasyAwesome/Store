package com.github.company.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "comments")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Comment {

    private long id;
    @NotBlank
    private String text;
    @NotNull
    private Date date = new Date();
    @NotNull
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false, length = 300, columnDefinition = "TEXT")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne(optional = false)
    @JoinColumn
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id &&
                Objects.equals(text, comment.text) &&
                Objects.equals(date, comment.date) &&
                Objects.equals(user, comment.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, date, user);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}