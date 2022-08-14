package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.request.CategoryRequest;
import com.example.demo.request.ProductRequest;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public String getAllProduct(Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("products",productService.getProduct());
        return "index";
    }

    @GetMapping("/{id_product}")
    public String deleteProduct(@PathVariable("id_product") String id_product){
        productService.deleteProduct(id_product);
        return "redirect:/home";
    }


    @GetMapping("/category")
    public String getProductByCategory(@RequestParam("category") String category, Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("products",productService.findByCategory(category));
        return "index";
    }

    @GetMapping("/addCategory")
    public String getFormCategory(Model model){
        model.addAttribute("category",new CategoryRequest());
        return "addCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(@Valid @ModelAttribute("category")CategoryRequest request, Model model, BindingResult result){
        if(result.hasErrors()){
            return "addCategory";
        }
        categoryService.insertCategory(request);
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("products",productService.getProduct());
        return "redirect:/home";
    }

    @GetMapping("/addProduct")
    public String getFormAddProduct(Model model){
        model.addAttribute("product",new ProductRequest());
        model.addAttribute("categories",categoryService.getAllCategory());
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@Valid @ModelAttribute("product")ProductRequest request, Model model, BindingResult result){
        if(result.hasErrors()){
            return "addProduct";
        }
        productService.insertProduct(request);
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("products",productService.getProduct());
        return "redirect:/home";
    }

    @GetMapping("/editProduct/{id}")
    public String getFormEditProduct(Model model, @PathVariable("id") String id){
        Product product = productService.getProductById(id).get();
        model.addAttribute("product", product);
        model.addAttribute("categories",categoryService.getAllCategory());
        return "editProduct";
    }

    @PostMapping("/editProduct/{id}")
    public String editProduct(@Valid @ModelAttribute("product")ProductRequest request, Model model, BindingResult result){
        if(result.hasErrors()){
            return "editProduct";
        }
        productService.updateProduct(request);
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("products",productService.getProduct());
        return "redirect:/home";
    }
}
