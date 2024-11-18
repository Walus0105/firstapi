package com.project1.firstapi.Product;

import com.project1.firstapi.Category.Category;
import com.project1.firstapi.Category.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductServiceImpl productService;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private CategoryRepository categoryRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUpProductControllerTest() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void getAllProducts() throws Exception {
        when(productService.findAllProducts()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/rest/products"))
                .andExpect(status().isOk());
    }

    @Test
    void getProductById() throws Exception {
        Product product = new Product("modelName", new BigDecimal("100.00"), "materialName", new Category("categoryName", "categoryDescription"));
        ProductDto productDto = new ProductDto("modelName", new BigDecimal("100.00"), "materialName", product.getCategory());

        when(productService.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(productDto);

        mockMvc.perform(get("/rest/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value("modelName"));
    }

    @Test
    void updateProduct() throws Exception {
        Product existingProduct = new Product("modelName", new BigDecimal("100.00"), "materialName", new Category("categoryName", "categoryDescription"));
        ProductUpdateRequest updateRequest = new ProductUpdateRequest("updatedModel", new BigDecimal("200.00"), "updatedMaterial", existingProduct.getCategory());
        Product updatedProduct = new Product("updatedModel", new BigDecimal("200.00"), "updatedMaterial", existingProduct.getCategory());
        ProductDto productDto = new ProductDto("updatedModel", new BigDecimal("200.00"), "updatedMaterial", existingProduct.getCategory());

        when(productService.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productService.saveProduct(existingProduct)).thenReturn(updatedProduct);
        when(productMapper.toDto(updatedProduct)).thenReturn(productDto);

        String requestBody = """
                {
                    "model": "updatedModel",
                    "price": 200.00,
                    "material": "updatedMaterial",
                    "category": {
                        "id": 1,
                        "name": "categoryName",
                        "description": "categoryDescription"
                    }
                }
                """;

        mockMvc.perform(patch("/rest/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value("updatedModel"));
    }

    @Test
    void deleteProduct() throws Exception {
        mockMvc.perform(delete("/rest/products/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getProductsByCategory() throws Exception {
        Category category = new Category("categoryName", "categoryDescription");
        Product product = new Product("modelName", new BigDecimal("100.00"), "materialName", category);
        ProductDto productDto = new ProductDto("modelName", new BigDecimal("100.00"), "materialName", category);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productService.getProductsByCategory(category)).thenReturn(Collections.singletonList(product));
        when(productMapper.toDto(product)).thenReturn(productDto);

        mockMvc.perform(get("/rest/products/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].model").value("modelName"));
    }

    @Test
    void getProductsByCategoryName() throws Exception {
        Category category = new Category("categoryName", "categoryDescription");
        Product product = new Product("modelName", new BigDecimal("100.00"), "materialName", category);
        ProductDto productDto = new ProductDto("modelName", new BigDecimal("100.00"), "materialName", category);

        when(categoryRepository.findByName("categoryName")).thenReturn(Optional.of(category));
        when(productService.getProductsByCategory(category)).thenReturn(Collections.singletonList(product));
        when(productMapper.toDto(product)).thenReturn(productDto);

        mockMvc.perform(get("/rest/products")
                        .param("category", "categoryName"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].model").value("modelName"));
    }

    @Test
    void createNewProduct() throws Exception {
        ProductRequest request = new ProductRequest("modelName", new BigDecimal("100.00"), "materialName", 1L);
        Category category = new Category("categoryName", "categoryDescription");
        Product product = new Product("modelName", new BigDecimal("100.00"), "materialName", category);
        ProductDto productDto = new ProductDto("modelName", new BigDecimal("100.00"), "materialName", category);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productService.saveProduct(product)).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(productDto);

        String requestBody = """
                {
                    "model": "modelName",
                    "price": 100.00,
                    "material": "materialName",
                    "categoryId": 1
                }
                """;

        mockMvc.perform(post("/rest/products/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model").value("modelName"));
    }
}
