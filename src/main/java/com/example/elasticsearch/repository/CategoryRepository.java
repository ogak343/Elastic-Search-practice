package com.example.elasticsearch.repository;

import com.example.elasticsearch.model.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    boolean existsByName(String name);
}
