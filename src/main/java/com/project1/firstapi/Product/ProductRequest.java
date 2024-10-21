package com.project1.firstapi.Product;

import java.math.BigDecimal;

public record ProductRequest(String model, BigDecimal price, String material, Long categoryId){

}