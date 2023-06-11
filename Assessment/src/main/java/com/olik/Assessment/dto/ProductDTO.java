package com.olik.Assessment.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String category;
    private String image;
    private double costPerHour;
    private double costPerDay;
    private double costPerWeek;


    public ProductDTO(String name, String category, String image, double costPerHour, double costPerDay, double costPerWeek) {
        this.name = name;
        this.category = category;
        this.image = image;
        this.costPerHour = costPerHour;
        this.costPerWeek = costPerWeek;
        this.costPerDay = costPerDay;
    }

    public ProductDTO() {

    }
}
