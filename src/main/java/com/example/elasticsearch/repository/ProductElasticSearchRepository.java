package com.example.elasticsearch.repository;

import com.example.elasticsearch.model.query.ProductQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;

public interface ProductElasticSearchRepository extends ElasticsearchRepository<ProductQuery, Long> {

    @Query("""
            {
              "bool": {
                "should": [
                  {
                    "multi_match": {
                      "query": "?0",
                      "fields": ["name^2", "description^3"],
                      "type": "best_fields",
                      "operator": "or"
                    }
                  },
                  {
                    "match_phrase": {
                      "movie": {
                        "query": "?0",
                        "boost": 3
                      }
                    }
                  },
                  {
                    "match_phrase": {
                      "overview": {
                        "query": "?0",
                        "boost": 2
                      }
                    }
                  },
                  {
                    "nested": {
                      "path": "category",
                      "query": {
                        "match": {
                          "category.name": {
                            "query": "?0",
                            "operator": "or"
                          }
                        }
                      }
                    }
                  }
                ],
                "minimum_should_match": 1
              }
            }
          """)
    Page<ProductQuery> search(@Param("query") String query, Pageable pageable);
}
