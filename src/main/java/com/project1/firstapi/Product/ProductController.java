package com.project1.firstapi.Product;

import com.project1.firstapi.Category.Category;
import com.project1.firstapi.Category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

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

    @GetMapping(params = {"category"})
    public List<ProductDto> getProductsByCategoryName(@RequestParam String category) {
        Category foundCategory = categoryRepository.findByName(category)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return productService.getProductsByCategory(foundCategory)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }



    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        Category category = categoryRepository.findById(productRequest.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));


        Product new_product = new Product(productRequest.model(), productRequest.price(), productRequest.material(), category);
        Product savedProduct = productService.saveProduct(new_product);
        return ResponseEntity.ok(savedProduct);
    }

    @PostMapping("/add")
    @ResponseStatus(CREATED)
    public ProductDto createNewProduct(@RequestBody @Validated ProductCreationRequest productCreationRequest) {
        Category category = categoryRepository.findById(productCreationRequest.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Product newProduct = new Product(
                productCreationRequest.getModel(),
                productCreationRequest.getPrice(),
                productCreationRequest.getMaterial(),
                category
        );
        Product savedProduct = productService.saveProduct(newProduct);
        return productMapper.toDto(savedProduct);
    }
}
