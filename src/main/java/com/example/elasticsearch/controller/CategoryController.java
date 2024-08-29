package com.example.elasticsearch.controller;

import com.example.elasticsearch.dto.request.CategoryCreate;
import com.example.elasticsearch.dto.request.CategoryUpdate;
import com.example.elasticsearch.dto.response.CategoryResponse;
import com.example.elasticsearch.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody @Valid CategoryCreate category) {

        log.info("Create category: {}", category);

        return ResponseEntity.ok(service.create(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> get(@PathVariable Long id) {

        log.info("Get category: {}", id);

        return ResponseEntity.ok(service.get(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable("id") Long id, @RequestBody CategoryUpdate category) {

        log.info("Update category, id: {}, dto: {}", id, category);

        return ResponseEntity.ok(service.update(id, category));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponse>> getAll() {

        log.info("Get all category");

        return ResponseEntity.ok(service.getAll());
    }
}
