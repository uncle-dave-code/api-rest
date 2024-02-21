package com.uncledavecode.api_rest.model.entities;

import com.uncledavecode.api_rest.model.keys.OrderDetailsPK;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orderdetails")
@IdClass(OrderDetailsPK.class)
@Getter
@Setter
public class OrderDetails {

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "total", nullable = false)
    private Double total;
}
