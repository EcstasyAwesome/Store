package com.github.company.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "wishes")
public class Wish {

    private long id;
    @NotNull
    private User user;
    @NotNull
    private Product product;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(optional = false)
    @JoinColumn
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product products) {
        this.product = products;
    }

    public Wish() {
    }

    public Wish(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wish wish = (Wish) o;
        return id == wish.id &&
                Objects.equals(user, wish.user) &&
                Objects.equals(product, wish.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, product);
    }

    @Override
    public String toString() {
        return "Wish{" +
                "id=" + id +
                ", user=" + user +
                ", product=" + product +
                '}';
    }
}