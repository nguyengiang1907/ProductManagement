package com.example.product_management.repository;

import com.example.product_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}
