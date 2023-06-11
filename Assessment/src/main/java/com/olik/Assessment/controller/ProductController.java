package com.olik.Assessment.controller;

import com.olik.Assessment.co.GetProductCO;
import com.olik.Assessment.dto.ProductDTO;
import com.olik.Assessment.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts(@RequestBody GetProductCO request) {
        List<ProductDTO> productList = productService.getAllProducts(request);
        return ResponseEntity.ok(productList);
    }
}
