package com.example.elasticsearch.mapper;

import com.example.elasticsearch.dto.request.ProductCreate;
import com.example.elasticsearch.dto.response.ProductResponse;
import com.example.elasticsearch.dto.request.ProductUpdate;
import com.example.elasticsearch.model.entity.ProductEntity;

import com.example.elasticsearch.model.query.ProductQuery;
import org.mapstruct.Mapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        implementationName = "ProductMapperImpl",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    ProductResponse toResponse(ProductEntity productEntity);

    @Mapping(target = "category.id", source = "categoryId")
    ProductEntity toEntity(ProductCreate create);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void patch(@MappingTarget ProductEntity entity, ProductUpdate dto);

    ProductQuery toDocument(ProductEntity entity);

}
