package com.example.product_management.service.user;

import com.example.product_management.model.User;
import com.example.product_management.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRepository;
    @Override
    public Iterable<User> findAll() {
        return iUserRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return iUserRepository.findById(id);
    }

    @Override
    public void save(User user) {
        iUserRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        iUserRepository.deleteById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return iUserRepository.findUserByEmail(email);
    }
}
