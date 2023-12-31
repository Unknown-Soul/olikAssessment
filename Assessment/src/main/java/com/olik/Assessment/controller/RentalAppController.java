package com.olik.Assessment.controller;

import com.olik.Assessment.co.GetProductCO;
import com.olik.Assessment.co.RentalBookingRequestCO;
import com.olik.Assessment.dto.ProductDTO;
import com.olik.Assessment.services.ProductService;
import com.olik.Assessment.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class RentalAppController {

    @Autowired
    RentalService rentalService;


    @PostMapping("/book/product")
    public ResponseEntity<String> bookRental(@RequestBody RentalBookingRequestCO request) {
        String message = rentalService.bookProduct(request);
        return ResponseEntity.ok(message);
    }

}
