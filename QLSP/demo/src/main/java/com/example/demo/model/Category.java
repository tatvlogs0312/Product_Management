package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Table(name = "category")
@Entity(name = "category")
@Getter
@Setter
public class Category {
    @Id
    private String category_id;

    private String category;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,orphanRemoval = false)
    private List<Product> products;

    public Category(String category){
        this.category_id = UUID.randomUUID().toString();
        this.category = category;
    }
}
