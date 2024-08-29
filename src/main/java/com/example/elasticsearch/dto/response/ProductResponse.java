package com.example.elasticsearch.dto.response;

public record ProductResponse(
        Long id,
        String name,
        String description,
        Long price,
        CategoryResponse category,
        int quantity
) {
}
