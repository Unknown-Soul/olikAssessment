package com.olik.Assessment.co;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RentalBookingRequestCO {
    private Long productId;
    private String startDate;
    private String endDate;
}
