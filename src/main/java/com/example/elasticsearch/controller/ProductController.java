package com.example.elasticsearch.controller;

import com.example.elasticsearch.dto.request.ProductCreate;
import com.example.elasticsearch.dto.response.ProductResponse;
import com.example.elasticsearch.dto.request.ProductUpdate;
import com.example.elasticsearch.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/products")
@Slf4j
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductCreate create) {

        log.info("Create product: {}", create);
        return ResponseEntity.ok(service.create(create));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> get(@PathVariable("id") Long id) {

        log.info("Get product: {}", id);
        return ResponseEntity.ok(service.get(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable("id") Long id, @RequestBody ProductUpdate dto) {

        log.info("Update product: {}", dto);
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping("/search")
    @Operation(summary = "Search similar Products by name, description and category name")
    public ResponseEntity<Page<ProductResponse>> search(
            @RequestParam("query") String query,
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size
    ) {

        log.info("Search products: page: {}, size: {}, query: {}", page, size, query);
        return ResponseEntity.ok(service.search(page, size, query));
    }
}
