package com.example.demo.metamodel;

import com.example.demo.model.Category;
import com.example.demo.model.Product;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.security.Signature;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {
    public static volatile SingularAttribute<Product,String> id_product;
    public static volatile SingularAttribute<Product,String> name;
    public static volatile SingularAttribute<Product,String> description;
    public static volatile SingularAttribute<Product, Category> category;
    public static final String ID_PRODUCT = "id_product";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String CATEGORY = "category";
}
