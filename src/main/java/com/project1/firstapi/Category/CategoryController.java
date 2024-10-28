package com.project1.firstapi.Category;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
}
