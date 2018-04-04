package com.github.company.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    private long id;
    @NotNull
    private ProductLine productLine;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String image;
    @Positive
    private double price;
    @Positive
    private int vote;
    @Positive
    private float rating;
    private boolean available;
    private Set<ProductComment> comments;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
    @JoinColumn
    public ProductLine getProductLine() {
        return productLine;
    }

    public void setProductLine(ProductLine productLine) {
        this.productLine = productLine;
    }

    @Column(nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false, length = 500, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Column(nullable = false)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(nullable = false)
    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    @Column(nullable = false, length = 1)
    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Column(nullable = false)
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "product")
    public Set<ProductComment> getComments() {
        return comments;
    }

    public void setComments(Set<ProductComment> comments) {
        this.comments = comments;
    }

    public Product() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        if (id != that.id) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (available != that.available) return false;
        if (productLine != null ? !productLine.equals(that.productLine) : that.productLine != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (vote != that.vote) return false;
        if (Float.compare(that.rating, rating) != 0) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + Long.hashCode(id);
        result = 31 * result + (productLine != null ? productLine.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + Double.hashCode(price);
        result = 31 * result + Float.hashCode(rating);
        result = 31 * result + Integer.hashCode(vote);
        result = 31 * result + Boolean.hashCode(available);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }
}