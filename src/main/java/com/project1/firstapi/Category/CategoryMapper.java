package com.project1.firstapi.Category;


import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDto toDto(Category category) {
        return new CategoryDto(category.getName(), category.getDescription());
    }
}
