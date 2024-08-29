package com.example.elasticsearch.repository;

import com.example.elasticsearch.model.query.ProductQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductElasticSearchRepository extends ElasticsearchRepository<ProductQuery, Long> {

    @Query("""
           {
                "bool": {
                  "should": [
                    {
                      "fuzzy": {
                        "name": {
                          "value": "?0",
                          "fuzziness": "AUTO"
                        }
                      }
                    },
                    {
                      "fuzzy": {
                        "description": {
                          "value": "?0",
                          "fuzziness": "AUTO"
                        }
                      }
                    },
                    {
                      "nested": {
                        "path": "category",
                        "query": {
                          "fuzzy": {
                            "category.name": {
                              "value": "?0",
                              "fuzziness": "AUTO"
                            }
                          }
                        }
                      }
                    }
                  ]
                }
              }
            """)
    Page<ProductQuery> search(String query, Pageable pageable);
}
