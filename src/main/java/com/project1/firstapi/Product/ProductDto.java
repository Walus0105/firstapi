package com.project1.firstapi.Product;

import com.project1.firstapi.Category.Category;
import java.math.BigDecimal;

public record ProductDto(String model, BigDecimal price , String material, Category category) {
}
