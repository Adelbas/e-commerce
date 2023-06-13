package com.adel.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
//    @GetMapping("/")
//    public String getHomePage(){
//        return "index";
//    }
    @GetMapping("/contact")
    public String getContact(){
        return "contact";
    }

//    @GetMapping("/products")
//    public String getProductsPage(){
//        return "products";
//    }
//    @GetMapping("/products/1")
//    public String getProductPage(){
//        return "product-details";
//    }


//    @GetMapping("/products/list")
//    public String getProductList(){
//        return "products-list";
//    }
//    @GetMapping("/products/create")
//    public String getProdCreate(){
//        return "product-create";
//    }
//    @GetMapping("/category")
//    public String getCatList(){
//        return "categories-list";
//    }
//    @GetMapping("/category/create")
//    public String getCatCreate(){
//        return "category-create";
//    }
}
