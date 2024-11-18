package com.project1.firstapi.Product;

import com.project1.firstapi.Category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductUpdateRequest productUpdateRequest){
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setModel(productUpdateRequest.getModel());
        existingProduct.setPrice(productUpdateRequest.getPrice());
        existingProduct.setMaterial(productUpdateRequest.getMaterial());
        existingProduct.setCategory(productUpdateRequest.getCategory());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) { throw new RuntimeException("Product not found"); }
        productRepository.deleteById(id);
    }
}
