package com.project1.firstapi.Category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryCreationRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public CategoryCreationRequest(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description) {
        this.name = name;
        this.description = description; }
}
