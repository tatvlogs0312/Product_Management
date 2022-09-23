package com.example.demo.service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.Category;
import com.example.demo.model.SearchCriteria;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CategorySpecification;
import com.example.demo.request.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void insertCategory(CategoryRequest request) {
        if(request.getCategory().equals("")){
            throw new BadRequestException("Bạn chưa nhập gì");
        }

        Optional<Category> category = categoryRepository.findById(request.getCategory());
        if (category.isPresent()) {
            throw new BadRequestException("Thể loại đã tồn tại");
        }
        Category newCategory = new Category(request.getCategory());
        categoryRepository.save(newCategory);
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Category getCategoryByProduct(String id){
//        Optional<Category> category = categoryRepository.findByProduct(id);
//        if(category.isPresent()){
//            throw new BadRequestException("Thể loại không tồn tại");
//        }
//        return category.get();
        CategorySpecification spec1 = new CategorySpecification(new SearchCriteria("id_product",":",id));
        List<Category> categories = categoryRepository.findAll((Sort) Specification.where(spec1));

        return categories.get(0);
    }
}
