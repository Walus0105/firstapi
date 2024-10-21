package com.project1.firstapi.Product;
import jdk.jfr.Category;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product) {
        return new ProductDto(product.getModel(), product.getPrice(), product.getMaterial(), product.getCategory());
    }
}
