package com.adel.ecommerce.controller;

import com.adel.ecommerce.model.Category;
import com.adel.ecommerce.model.Product;
import com.adel.ecommerce.service.CategoryService;
import com.adel.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;

    private final CategoryService categoryService;

    @GetMapping("/products/list")
    public String listProducts(Model model){
        model.addAttribute("products",productService.listProducts(null));
        return "products-list";
    }

    @GetMapping("/products/create")
    public String editProducts(Product product, Model model){
        model.addAttribute("categories",categoryService.listCategory(null));
        return "product-create";
    }

    @PostMapping("/products/create")
    public String createProduct(@RequestParam("files") List<MultipartFile> files, @Valid Product product, BindingResult bindingResult, Model model) throws IOException {
        if(bindingResult.hasErrors() || files.size()==1 && files.get(0).getSize()==0){
            if(files.size()==1 && files.get(0).getSize()==0)
                bindingResult.rejectValue("images","error.Product","Product must have images!");
            model.addAttribute("categories",categoryService.listCategory(null));
            return "product-create";
        }

        productService.saveProduct(product, files);
        return "redirect:/admin/products/list";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products/list";
    }

    @GetMapping("/category")
    public String categories(Model model) {
        model.addAttribute("categories", categoryService.listCategory(null));
        return "categories-list";
    }

    @GetMapping("/category/create")
    public String categoriesCreate(Category category,Model model) {
        model.addAttribute("category",category);
        return "category-create";
    }

    @PostMapping("/category/create")
    public String createCategory(@Valid Category category, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "category-create";
        if(!categoryService.listCategory(category.getName()).isEmpty()){
            bindingResult.rejectValue("name","error.Category","This category already exists!");
            return "category-create";
        }
        categoryService.saveCategory(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/category";
    }
}
