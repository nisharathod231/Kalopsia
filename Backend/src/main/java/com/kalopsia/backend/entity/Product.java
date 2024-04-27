package com.kalopsia.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "old_price")
    private int oldPrice;

    @Column(name = "new_price")
    private int newPrice;

    private String image;

    private String category;

    public Product(String name, int oldPrice, int newPrice, String image, String category) {
        this.name = name;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.image = image;
        this.category = category;
    }
}
