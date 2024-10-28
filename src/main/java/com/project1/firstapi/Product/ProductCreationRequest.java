package com.project1.firstapi.Product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project1.firstapi.Category.Category;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;


@Data
public final class ProductCreationRequest {

    @NotNull
    private final String model;

    @NotNull
    private final String material;

    @NotNull
    @Positive
    private final BigDecimal price;

    @NotNull
    @Positive
    private final Category category;

    @JsonCreator
    public ProductCreationRequest(
            @JsonProperty("model") @NotNull String model,
            @JsonProperty("material") @NotNull String material,
            @JsonProperty("price") @Positive BigDecimal price,
            @JsonProperty("category") @NotNull Category category) {

        this.model = model;
        this.material = material;
        this.price = price;
        this.category = category;
    }
}
