package com.example.elasticsearch.exception;

import com.example.elasticsearch.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorResponse> handleCustomerException(CustomerException ex) {

        var errorCode = ex.getErrorCode();
        log.error("Error occurred, status: {}, message: {}", errorCode.getCode(), errorCode.getMessage());

        return ResponseEntity.status(errorCode.getCode())
                .body(new ErrorResponse(errorCode.getCode(), errorCode.getMessage()));
    }
}
