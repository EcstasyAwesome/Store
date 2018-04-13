package com.github.company.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    private long id;
    @NotNull
    private User user;
    @NotEmpty
    private List<OrderItem> orderItems;
    @NotNull
    private Date date = new Date();
    @Positive
    private double sum;

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

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "order")
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(nullable = false)
    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Order() {
    }

    public Order(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Double.compare(order.sum, sum) == 0 &&
                Objects.equals(user, order.user) &&
                Objects.equals(orderItems, order.orderItems) &&
                Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, orderItems, date, sum);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", orderItems=" + orderItems +
                ", date=" + date +
                ", sum=" + sum +
                '}';
    }
}