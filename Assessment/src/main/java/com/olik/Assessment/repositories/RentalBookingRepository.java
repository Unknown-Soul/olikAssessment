package com.olik.Assessment.repositories;

import com.olik.Assessment.entities.Category;
import com.olik.Assessment.entities.RentalBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalBookingRepository extends JpaRepository<RentalBooking, Long> {

}