package com.example.elasticsearch.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProductCreate(
        @NotEmpty String name,
        @NotEmpty String description,
        @NotNull Long price,
        @NotNull Integer quantity,
        @NotNull Long categoryId
) {
}
