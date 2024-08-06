package com.enoca.ecommorce.entities.concretes;

import com.enoca.ecommorce.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "carts")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Cart extends BaseEntity {

    @OneToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany()
    @JoinTable(
            name = "cart_products",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
}
