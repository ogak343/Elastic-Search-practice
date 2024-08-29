package com.example.elasticsearch.mapper;

import com.example.elasticsearch.dto.request.CategoryCreate;
import com.example.elasticsearch.dto.request.CategoryUpdate;
import com.example.elasticsearch.dto.response.CategoryResponse;
import com.example.elasticsearch.model.entity.CategoryEntity;

import org.mapstruct.Mapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        implementationName = "CategoryMapperImpl",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    CategoryResponse toResponseDto(CategoryEntity save);

    CategoryEntity toEntity(CategoryCreate category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void patch(@MappingTarget CategoryEntity entity, CategoryUpdate category);
}
