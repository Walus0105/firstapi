package com.project1.firstapi.Product;

import com.project1.firstapi.Category.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "material",  nullable = false)
    private String material;



    @JoinColumn(name = "category_id", nullable = false   )
    @ManyToOne(cascade = CascadeType.DETACH)
    private Category category;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String name) {
        this.model = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Category getCategory() {
        return category;
    }


    public void setCategory(Category category) {
        this.category = category;
    }



    public Product(String model, BigDecimal price , String material, Category category) {
        this.model = model;
        this.price = price;
        this.material = material;
        this.category = category;
    }

}
