package com.example.elasticsearch.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record CategoryCreate(
        @NotEmpty String name,
        @NotEmpty String description
) {
}
