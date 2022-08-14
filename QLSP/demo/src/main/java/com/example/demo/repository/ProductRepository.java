package com.example.demo.repository;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(value = "SELECT * FROM product p INNER JOIN category c ON p.category_id = c.category_id WHERE c.category = ?1",
            nativeQuery = true)
    public List<Product> findByCategory(@Param("category") String category);
}