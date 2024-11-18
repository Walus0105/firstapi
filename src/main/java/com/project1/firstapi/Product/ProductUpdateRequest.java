package com.project1.firstapi.Product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project1.firstapi.Category.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductUpdateRequest {
     @JsonProperty("id")
     private long id;

     @JsonProperty("model")
     private String model;

     @JsonProperty("price")
     private BigDecimal price;

     @JsonProperty("material")
     private String material;

     @JsonProperty("category") private
     Category category;

     public ProductUpdateRequest
             (@JsonProperty("model") String model,
              @JsonProperty("price") BigDecimal price,
              @JsonProperty("material") String material,
              @JsonProperty("category") Category category) {
         this.model = model;
         this.price = price;
         this.material = material;
         this.category = category;
     }
}
