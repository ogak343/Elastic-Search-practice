package com.example.elasticsearch.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Setter
@Getter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long price;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
    private int quantity;
}
