package com.example.demo.repository;

import com.example.demo.metamodel.Product_;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;

@Component
public class PCSpecification {

    public Specification<Product> getNameProduct(String name){
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get(Product_.NAME),"%"+name+"%");
            }
        };
    }

    public Specification<Product> getProductByCategory(String category){
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Join<Product, Category> categoryJoin = root.join(Product_.CATEGORY,JoinType.LEFT);
                return criteriaBuilder.equal(categoryJoin.get(Product_.CATEGORY),category);
            }
        };
    }

    public Specification<Category> getCategoryByProductID(String id){
        return new Specification<Category>() {
            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Join<Category,Product> productJoin = root.join("products",JoinType.LEFT);
                return criteriaBuilder.equal(productJoin.get("id_product"),id);
            }
        };
    }
}
