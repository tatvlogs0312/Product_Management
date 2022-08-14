package com.example.demo.controller;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotfoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.request.ProductRequest;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/product")
    public ResponseEntity<?> getAllProduct() {
        List<Product> products = productService.getProduct();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable String id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new NotfoundException("Không có sản phẩm");
    }

    @PostMapping("/product")
    public ResponseEntity<Product> insertProduct(@RequestBody ProductRequest productRequest) {
        Product newProduct = productService.insertProduct(productRequest);
        return ResponseEntity.ok(newProduct);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/product")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductRequest productRequest) {
        Product product = productService.updateProduct(productRequest);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getAllCategory() {
        List<Category> categories = categoryService.getAllCategory();
        return ResponseEntity.ok(categories);
    }
}
