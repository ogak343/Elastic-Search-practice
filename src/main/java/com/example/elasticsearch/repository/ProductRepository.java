package com.example.elasticsearch.repository;

import com.example.elasticsearch.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByName(String name);
}
