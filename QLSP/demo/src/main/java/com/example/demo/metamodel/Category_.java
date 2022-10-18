package com.example.demo.metamodel;

import com.example.demo.model.Category;
import com.example.demo.model.Product;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category.class)
public abstract class Category_ {
    public static volatile SingularAttribute<Category,String> id_category;
    public static volatile ListAttribute<Category, Product> products;
    public static final String ID_CATEGORY = "id_category";
    public static final String PRODUCTS = "products";
}
