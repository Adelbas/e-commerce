package com.adel.ecommerce.controller;

import com.adel.ecommerce.model.User;
import com.adel.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("")
    public String getCart(){
        return "cart";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, @AuthenticationPrincipal User user, int quantity){
        System.out.println(user.getName());
//        System.out.println(principal.getName());
        System.out.println(user.getId());
        System.out.println(id);
        System.out.println(quantity);
        cartService.addProduct(user.getId(),id,quantity);
        return "redirect:/products/{id}";
    }
}
