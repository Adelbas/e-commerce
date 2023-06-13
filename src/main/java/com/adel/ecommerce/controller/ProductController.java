package com.adel.ecommerce.controller;

import com.adel.ecommerce.model.Product;
import com.adel.ecommerce.model.User;
import com.adel.ecommerce.service.CartService;
import com.adel.ecommerce.service.CategoryService;
import com.adel.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("")
    public String products(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("products", productService.listProducts(name));
        return "products";
    }

    @GetMapping("/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("products", productService.getRelatedProducts(id));
        return "product-details";
    }

}

