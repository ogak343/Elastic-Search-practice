package com.example.elasticsearch.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProductUpdate(
        @NotNull Long id,
        @NotEmpty String name,
        @NotEmpty String description,
        @NotNull Long price,
        @NotNull Integer quantity
) {
}
