package com.example.demo.repository;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class CategorySpecification implements Specification<Category> {

    private SearchCriteria criteria;

    @Override
    public Specification<Category> and(Specification<Category> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<Category> or(Specification<Category> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Category, Product> categoryProductJoin = root.join("products");
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(categoryProductJoin.get(criteria.getKey()),
                    criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(categoryProductJoin.get(criteria.getKey()),
                    criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (categoryProductJoin.get(criteria.getKey()).getJavaType() != String.class) {
                return criteriaBuilder.equal(categoryProductJoin.get(criteria.getKey()), criteria.getValue());
            } else {
                return criteriaBuilder.like(categoryProductJoin.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            }
        }
        return null;
    }

    public CategorySpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }
}
