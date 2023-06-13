package com.adel.ecommerce.service;

import com.adel.ecommerce.model.Category;
import com.adel.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void saveCategory(Category category){
        categoryRepository.save(category);
    }

    public List<Category> listCategory(String name){
        if(name!=null) return categoryRepository.findByName(name);
        return categoryRepository.findAll();
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
