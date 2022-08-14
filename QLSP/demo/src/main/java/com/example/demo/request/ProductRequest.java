package com.example.demo.request;

import com.example.demo.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private String category;
}
