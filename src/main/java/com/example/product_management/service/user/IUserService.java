package com.example.product_management.service.user;

import com.example.product_management.model.User;
import com.example.product_management.service.IGenerateService;

import java.util.Optional;

public interface IUserService extends IGenerateService<User> {
    User findUserByEmail(String email);
}

