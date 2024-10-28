package com.project1.firstapi.Product;

import com.project1.firstapi.Category.Category;
import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

    List<Product> getProductsByCategory(Category category);

    Product saveProduct(Product product);
}
