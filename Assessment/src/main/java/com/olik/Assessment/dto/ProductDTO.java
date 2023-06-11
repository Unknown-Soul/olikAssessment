package com.olik.Assessment.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String category;
    private String image;
    private double costPerHour;

    public ProductDTO(String name, String category, String image, double costPerHour) {
        this.name = name;
        this.category = category;
        this.image = image;
        this.costPerHour = costPerHour;
    }

    public ProductDTO() {

    }
}
