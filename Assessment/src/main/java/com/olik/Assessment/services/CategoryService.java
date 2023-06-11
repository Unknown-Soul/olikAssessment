package com.olik.Assessment.services;

import com.olik.Assessment.entities.Category;
import com.olik.Assessment.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        return  categoryRepository.findAll();
    }
}
