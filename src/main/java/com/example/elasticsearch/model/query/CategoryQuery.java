package com.example.elasticsearch.model.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
public class CategoryQuery {

    @Id
    private Long id;
    @Field(type = FieldType.Text, analyzer = "english_analyzer")
    private String name;
}
