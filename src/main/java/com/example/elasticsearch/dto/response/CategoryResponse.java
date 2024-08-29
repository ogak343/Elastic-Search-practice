package com.example.elasticsearch.dto.response;

public record CategoryResponse(
        Long id,
        String name,
        String description
) {
}
