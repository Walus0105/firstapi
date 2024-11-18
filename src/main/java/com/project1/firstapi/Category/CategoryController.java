package com.project1.firstapi.Category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/rest/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryMapper categoryMapper;
    private final CategoryServiceImpl categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.findAllCategories()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<CategoryDto> createNewCategory(@RequestBody @Validated CategoryCreationRequest categoryCreationRequest) {
        Category newCategory = new Category(categoryCreationRequest.getName(), categoryCreationRequest.getDescription());
        Category savedCategory = categoryService.saveCategory(newCategory);
        return ResponseEntity.ok(categoryMapper.toDto(savedCategory));
    }
}
