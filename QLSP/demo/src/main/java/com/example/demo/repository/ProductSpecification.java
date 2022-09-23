package com.example.demo.repository;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ProductSpecification implements Specification<Product> {

    private SearchCriteria criteria;

    @Override
    public Specification<Product> and(Specification<Product> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<Product> or(Specification<Product> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Product, Category> productCategoryJoin = root.join("category");
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(productCategoryJoin.get(criteria.getKey()),
                    criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(productCategoryJoin.get(criteria.getKey()),
                    criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (productCategoryJoin.get(criteria.getKey()).getJavaType() != String.class) {
                return criteriaBuilder.equal(productCategoryJoin.get(criteria.getKey()), criteria.getValue());
            } else {
                return criteriaBuilder.like(productCategoryJoin.get(criteria.getKey()),"%" + criteria.getValue() + "%");
            }
        }
        return null;
    }

    public ProductSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }
}
