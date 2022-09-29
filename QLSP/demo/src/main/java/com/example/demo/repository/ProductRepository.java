package com.example.demo.repository;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
//    @Query(value = "SELECT * FROM product p INNER JOIN category c ON p.category_id = c.category_id WHERE c.category = ?1",
//            nativeQuery = true)
//    public List<Product> findByCategory(@Param("category") String category);

    List<Product> findByNameLikeIgnoreCase(String name);

    @Query(value = "select * from product p where p.name like %?1%", nativeQuery = true)
    List<Product> findByName(String name);
}