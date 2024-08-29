package com.example.elasticsearch.model.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "product_id_index")
@Setting(settingPath = "/elasticsearch-settings.json")
@Getter
@Setter
@NoArgsConstructor
public class ProductQuery {

    @Id
    private Long id;
    @Field(type = FieldType.Text, analyzer = "english_analyzer")
    private String name;
    @Field(type = FieldType.Text, analyzer = "english_analyzer")
    private String description;
    @Field(type = FieldType.Long)
    private Long price;
    @Field(type = FieldType.Nested, includeInParent = true)
    private CategoryQuery category;

}
