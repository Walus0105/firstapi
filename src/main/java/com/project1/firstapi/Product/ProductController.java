package com.project1.firstapi.Product;

import com.project1.firstapi.Category.Category;
import com.project1.firstapi.Category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductMapper productMapper;
    private final ProductServiceImpl productService;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.findAllProducts()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());

    }
    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        List<ProductDto> products = productService.getProductsByCategory(category)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        Category category = categoryRepository.findById(productRequest.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));


        Product new_product = new Product(productRequest.model(), productRequest.price(), productRequest.material(), category);
        Product savedProduct = productService.saveProduct(new_product);
        return ResponseEntity.ok(savedProduct);
    }


}
