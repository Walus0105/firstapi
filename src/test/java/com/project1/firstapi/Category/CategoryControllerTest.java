package com.project1.firstapi.Category;

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

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryMapper categoryMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUpCategoryControllerTest() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void getAllCategories() throws Exception {
        when(categoryService.findAllCategories()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/rest/categories"))
                .andExpect(status().isOk());
    }

    @Test
    void createNewCategory() throws Exception {
        CategoryCreationRequest request = new CategoryCreationRequest("categoryName", "categoryDescription");
        Category category = new Category("categoryName", "categoryDescription");
        CategoryDto categoryDto = new CategoryDto("categoryName", "categoryDescription");

        when(categoryService.saveCategory(category)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(categoryDto);

        String requestBody = """
                {
                    "name": "categoryName",
                    "description": "categoryDescription"
                }
                """;

        mockMvc.perform(post("/rest/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("categoryName"));
    }
}
