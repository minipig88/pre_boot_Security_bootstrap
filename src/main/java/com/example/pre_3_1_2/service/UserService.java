package com.example.pre_3_1_2.service;

import com.example.pre_3_1_2.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    void update(User user, String[] roles);
    User findById(Long id);
    List<User> findAll();
    void deleteById(Long id);
}
