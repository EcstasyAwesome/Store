package com.github.company.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Entity
@Table(name = "orderItems")
public class OrderItem {

    private long id;
    @NotNull
    private Order order;
    @NotNull
    private Product product;
    @Positive
    private int amount;
    @Positive
    private double price;

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
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne(optional = false)
    @JoinColumn
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(nullable = false)
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Column(nullable = false)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderItem() {
    }

    public OrderItem(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return id == orderItem.id &&
                amount == orderItem.amount &&
                Double.compare(orderItem.price, price) == 0 &&
                Objects.equals(order, orderItem.order) &&
                Objects.equals(product, orderItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, product, amount, price);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}