package com.example.elasticsearch.dto.response;

public record ErrorResponse(
        int code,
        String message
) {
}
