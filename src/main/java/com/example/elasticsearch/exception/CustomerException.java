package com.example.elasticsearch.exception;

import com.example.elasticsearch.constants.ErrorCode;
import lombok.Getter;

@Getter
public class CustomerException extends RuntimeException {

    private final ErrorCode errorCode;
    public CustomerException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
