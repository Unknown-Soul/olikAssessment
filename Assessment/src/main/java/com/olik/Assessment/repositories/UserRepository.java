package com.olik.Assessment.repositories;

import com.olik.Assessment.entities.Category;
import com.olik.Assessment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
