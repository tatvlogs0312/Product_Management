package com.example.demo.service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotfoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired private ProductRepository productRepository;

    @Autowired private CategoryRepository categoryRepository;

    public List<Product> getProduct(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id){
        return productRepository.findById(id);
    }

    public Product insertProduct(ProductRequest request){
//        Optional<Product> optional = getProductById(request.getId());
//        if(optional.isPresent()){
//            throw new BadRequestException("Sản phẩm đã tồn tại");
//        }

        String id = UUID.randomUUID().toString();

        Optional<Category> category = categoryRepository.findByCategory(request.getCategory());
        if(category.isEmpty()){
            throw new BadRequestException("Thể loại không tồn tại");
        }

        Product newProduct = new Product(id,request.getName(),request.getDescription(),category.get());
        productRepository.save(newProduct);
        return newProduct;
    }

    public void deleteProduct(String id){
        Optional<Product> optional = getProductById(id);
        if(optional.isEmpty()){
            throw new BadRequestException("Sản phẩm không tồn tại");
        }
        Product product = optional.get();
        product.setCategory(null);
        productRepository.delete(product);
    }

    public Product updateProduct(ProductRequest request){
        Optional<Product> optional = getProductById(request.getId());
        if (optional.isEmpty()){
            throw new NotfoundException("Sản phẩm không tồn tại");
        }

        Optional<Category> category = categoryRepository.findByCategory(request.getCategory());
        if(category.isEmpty()){
            throw new BadRequestException("Thể loại không tồn tại");
        }

        Product product = optional.get();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategory(category.get());

        productRepository.save(product);

        return product;
    }

    public List<Product> findByCategory(String category){
        return productRepository.findByCategory(category);
    }

    public List<Product> getProductByName(String search){
        List<Product> products = productRepository.findAll();
        List<Product> lst = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(search.toLowerCase())) {
                lst.add(product);
            }
        }
        return lst;
    }
}
