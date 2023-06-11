package com.olik.Assessment.services;

import com.olik.Assessment.co.RentalBookingRequestCO;
import com.olik.Assessment.dto.ProductDTO;
import com.olik.Assessment.entities.Product;
import com.olik.Assessment.entities.RentalBooking;
import com.olik.Assessment.entities.User;
import com.olik.Assessment.repositories.CategoryRepository;
import com.olik.Assessment.repositories.ProductRepository;
import com.olik.Assessment.repositories.RentalBookingRepository;
import com.olik.Assessment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    RentalBookingRepository rentalBookingRepository;
    @Autowired
    UserRepository userRepository;

    public String bookProduct(RentalBookingRequestCO rentalBookingRequestCO) {
        Optional<Product> productOptional = productRepository.findById(rentalBookingRequestCO.getProductId());
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            //Todo
            /**
             *  we can put backend check to check end date is greataer than start date
             *  to change status to false we can create job .
             *  verify product is not already booked
             * */
            RentalBooking rentalBooking = new RentalBooking();
            rentalBooking.setProduct(product);
            rentalBooking.setStartDate(rentalBooking.getStartDate());
            rentalBooking.setEndDate(rentalBooking.getEndDate());
            Integer duration = (int) getDuration(rentalBookingRequestCO.getStartDate(), rentalBookingRequestCO.getEndDate());
            Double cost = duration * product.getCostPerHour() * 24 * duration;
            rentalBooking.setStartDate(getDate(rentalBookingRequestCO.getStartDate()));
            rentalBooking.setEndDate(getDate(rentalBookingRequestCO.getEndDate()));
            rentalBooking.setDuration(duration);
            // todo :  make enum of status
            rentalBooking.setStatus("ACTIVE");
            rentalBooking.setTotalCost(cost);
            // todo : hardCoded For now will take logged In User;
            User user = userRepository.findById(1L).get();
            rentalBooking.setUser(user);
            rentalBookingRepository.saveAndFlush(rentalBooking);
//            product.setBooked(true);
            productRepository.saveAndFlush(product);
            return "product saved successfully";
        }
        return "Product Not available for rent";

    }


    private long getDuration(String startDate, String endDate) {
        long daysDifference = ChronoUnit.DAYS.between(getDate(startDate), getDate(endDate));
        return daysDifference;
    }

    //todo: create different file that will have utils functions like this
    private LocalDate getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return LocalDate.parse(date, formatter);
    }
}
