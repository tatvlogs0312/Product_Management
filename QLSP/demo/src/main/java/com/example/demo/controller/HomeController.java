package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.request.CategoryRequest;
import com.example.demo.request.ProductRequest;
import com.example.demo.request.SearchRequest;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
        model.addAttribute("searchRequest", new SearchRequest());
        model.addAttribute("productRequest", new ProductRequest());
        model.addAttribute("categoryRequest", new CategoryRequest());
        return "index";
    }

    @GetMapping("/delete/{id_product}")
    public String deleteProduct(@PathVariable("id_product") String id_product){
        productService.deleteProduct(id_product);
        return "redirect:/home";
    }


    @GetMapping("/category")
    public String getProductByCategory(@RequestParam("category") String category, Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("products",productService.findByCategory(category));
        model.addAttribute("searchRequest", new SearchRequest());
        model.addAttribute("categoryRequest", new CategoryRequest());
        return "index";
    }

    @GetMapping("/addCategory")
    public String getFormCategory(Model model){
        model.addAttribute("category",new CategoryRequest());
        return "categoryAdd";
    }

    @PostMapping("/addCategory")
    public String addCategory(@Valid @ModelAttribute("category")CategoryRequest request, BindingResult result, Model model){
        if(result.hasErrors()){
            return "categoryAdd";
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
        return "productAdd";
    }


    @PostMapping("/addProduct")
    public String addProduct(@Valid @ModelAttribute("product") ProductRequest request , BindingResult result , Model model){
        if(result.hasErrors()){
            model.addAttribute("categories",categoryService.getAllCategory());
            return "productAdd";
        }
        productService.insertProduct(request);
        model.addAttribute("categories",categoryService.getAllCategory());
        return "redirect:/home";
    }

    @GetMapping("/editProduct/{id}")
    public String getFormEditProduct(Model model, @PathVariable("id") String id){
        Product product = productService.getProductById(id).get();
        ProductRequest request = new ProductRequest(product.getId_product(),
                product.getName(),product.getDescription(),product.getCategory().getCategory());
        model.addAttribute("product", request);
        model.addAttribute("categories",categoryService.getAllCategory());
        return "editProduct";
    }

    @PostMapping("/editProduct")
    public String editProduct(@Valid @ModelAttribute("product")ProductRequest request, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("categories",categoryService.getAllCategory());
            return "editProduct";
        }
        productService.updateProduct(request);
        model.addAttribute("categories",categoryService.getAllCategory());
        return "redirect:/home";
    }

    @GetMapping("/search")
    public String searchProduct(@ModelAttribute("searchRequest")SearchRequest request, Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("products",productService.getProductByName(request.getName()));
        model.addAttribute("searchRequest", new SearchRequest());
        model.addAttribute("categoryRequest", new CategoryRequest());
        return "index";
    }

    @GetMapping("/table")
    public String getTable(){
        return "table";
    }
}
