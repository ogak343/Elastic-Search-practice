package com.example.elasticsearch.constants;

import lombok.Getter;

@Getter
public enum ErrorCode {
    CATEGORY_EXISTS(409, "Category already exists"),
    PRODUCT_EXISTS(409, "Product already exists"),
    PRODUCT_NOT_FOUND(404, "Product not found"),
    CATEGORY_NOT_FOUND(404, "Category not found");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
