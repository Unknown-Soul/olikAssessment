package com.olik.Assessment.controller;

import com.olik.Assessment.co.GetProductCO;
import com.olik.Assessment.dto.ProductDTO;
import com.olik.Assessment.entities.Category;
import com.olik.Assessment.services.CategoryService;
import com.olik.Assessment.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categoryList = categoryService.getAllCategory();
        return ResponseEntity.ok(categoryList);
    }
}
