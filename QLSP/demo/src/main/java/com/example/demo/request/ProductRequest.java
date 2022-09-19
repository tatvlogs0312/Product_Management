package com.example.demo.request;

import com.example.demo.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
    private String id;

    @NotEmpty(message = "Không bỏ trống ô này")
    private String name;

    @NotEmpty(message = "Không bỏ trống ô này")
    @Max(value = 200 , message = "Không vượt quá 200 kí tự")
    private String description;

    private String category;
}
