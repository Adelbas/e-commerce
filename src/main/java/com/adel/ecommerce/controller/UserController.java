package com.adel.ecommerce.controller;

import com.adel.ecommerce.model.User;
import com.adel.ecommerce.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(User user, @RequestParam(value = "reg", required = false)String reg, Authentication authentication) {
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
//        if(SecurityContextHolder.getContext().getAuthentication()!=null &&
//                !(SecurityContextHolder.getContext().getAuthentication()
//                        instanceof AnonymousAuthenticationToken)
//        )
        if(authentication!=null)
            return "redirect:/account";
        return "login-page";
    }

    @PostMapping("/registration")
    public String registration(@Valid User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("reg","reg");
            return "login-page";
        }
        if(userService.loadUserByUsername(user.getEmail())!=null){
            bindingResult.rejectValue("email","error.Email","This email is already registered!");
            model.addAttribute("reg","reg");
            return "login-page";
        }
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        userService.createUser(user);
        return "redirect:/login";
    }
    @GetMapping("/registration")
    public String getRegistration(){
        return "redirect:/login";
    }
    @GetMapping("/account")
    public String getAccount(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user",user);
        return "user-page";
    }
}
