package com.project1.firstapi.Product;

import com.project1.firstapi.Category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ProductRepository  extends JpaRepository<Product, Long> {
            List<Product> findByCategory (Category category);
        }




