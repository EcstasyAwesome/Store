package com.github.company.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Objects;

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
    private List<ProductComment> comments;

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
    public List<ProductComment> getComments() {
        return comments;
    }

    public void setComments(List<ProductComment> comments) {
        this.comments = comments;
    }

    public Product() {
    }

    public Product(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.price, price) == 0 &&
                vote == product.vote &&
                Float.compare(product.rating, rating) == 0 &&
                available == product.available &&
                Objects.equals(productLine, product.productLine) &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(image, product.image) &&
                Objects.equals(comments, product.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productLine, name, description, image, price, vote, rating, available, comments);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productLine=" + productLine +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", vote=" + vote +
                ", rating=" + rating +
                ", available=" + available +
                ", comments=" + comments +
                '}';
    }
}