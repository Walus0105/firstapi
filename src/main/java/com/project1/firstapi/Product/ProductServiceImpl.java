package com.project1.firstapi.Product;

import com.project1.firstapi.Category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;



    @Override
    public List<Product> findAllProducts(){
        return  productRepository.findAll();

    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }



}
