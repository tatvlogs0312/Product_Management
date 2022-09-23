package com.example.demo.repository;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> , JpaSpecificationExecutor<Category> {

    Optional<Category> findByCategory(String category);

//    @Query(value = "SELECT * FROM category c INNER JOIN product p ON p.category_id = c.category_id WHERE p.product_id = ?1",
//            nativeQuery = true)
//    Optional<Category> findByProduct(@Param("id") String id);

}
