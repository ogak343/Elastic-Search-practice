package com.example.elasticsearch.service;

import com.example.elasticsearch.constants.ErrorCode;
import com.example.elasticsearch.dto.request.CategoryCreate;
import com.example.elasticsearch.dto.request.CategoryUpdate;
import com.example.elasticsearch.dto.response.CategoryResponse;
import com.example.elasticsearch.model.entity.CategoryEntity;
import com.example.elasticsearch.exception.CustomerException;
import com.example.elasticsearch.mapper.CategoryMapper;
import com.example.elasticsearch.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryResponse create(CategoryCreate category) {

        if (repository.existsByName(category.name()))
            throw new CustomerException(ErrorCode.CATEGORY_EXISTS);

        return mapper.toResponseDto(repository.save(mapper.toEntity(category)));
    }

    public CategoryResponse get(Long id) {
        return mapper.toResponseDto(getCategory(id));
    }

    public CategoryResponse update(Long id, CategoryUpdate category) {

        CategoryEntity entity = getCategory(id);

        mapper.patch(entity, category);
        return mapper.toResponseDto(repository.save(entity));
    }

    public List<CategoryResponse> getAll() {

        return repository.findAll().stream().map(mapper::toResponseDto).toList();
    }

    private CategoryEntity getCategory(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomerException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    public boolean existsById(Long categoryId) {
        return repository.existsById(categoryId);
    }
}
