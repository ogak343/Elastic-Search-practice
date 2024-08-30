package com.example.elasticsearch.service;

import com.example.elasticsearch.constants.ErrorCode;
import com.example.elasticsearch.dto.request.ProductCreate;
import com.example.elasticsearch.dto.response.ProductResponse;
import com.example.elasticsearch.dto.request.ProductUpdate;
import com.example.elasticsearch.model.entity.ProductEntity;
import com.example.elasticsearch.exception.CustomerException;
import com.example.elasticsearch.mapper.ProductMapper;
import com.example.elasticsearch.model.query.ProductQuery;
import com.example.elasticsearch.repository.ProductElasticSearchRepository;
import com.example.elasticsearch.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final CategoryService categoryService;
    private final ProductMapper mapper;
    private final ProductElasticSearchRepository elasticsearchRepository;

    public ProductResponse create(ProductCreate product) {

        if (repository.existsByName(product.name()))
            throw new CustomerException(ErrorCode.PRODUCT_EXISTS);

        if (!categoryService.existsById(product.categoryId()))
            throw new CustomerException(ErrorCode.CATEGORY_NOT_FOUND);

        var entity = repository.save(mapper.toEntity(product));

        saveDocument(entity);

        return mapper.toResponse(entity);
    }

    private void saveDocument(ProductEntity entity) {
        elasticsearchRepository.save(mapper.toDocument(entity));
    }

    public ProductResponse get(Long id) {
        return mapper.toResponse(getProduct(id));
    }

    public ProductResponse update(Long id, ProductUpdate dto) {

        ProductEntity entity = getProduct(id);

        mapper.patch(entity, dto);
        entity = repository.save(entity);
        saveDocument(entity);
        return mapper.toResponse(entity);
    }

    public Page<ProductResponse> search(Integer page, Integer size, String query) {

        Pageable pageable = PageRequest.of(page, size);

        Page<ProductQuery> searched = elasticsearchRepository.search(query, pageable);

        Map<Long, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < searched.getContent().size(); i++) {
            indexMap.put(searched.getContent().get(i).getId(), i);
        }

        var data = repository.findAllById(indexMap.keySet());
        data.sort(Comparator.comparingInt(product -> indexMap.get(product.getId())));

        return new PageImpl<>(data.stream().map(mapper::toResponse).toList(), pageable, searched.getTotalElements());
    }


    private ProductEntity getProduct(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomerException(ErrorCode.PRODUCT_NOT_FOUND));
    }
}
