package com.example.pre_3_1_2.service;

import com.example.pre_3_1_2.model.Role;

import java.util.Set;

public interface RoleService {
    void save(Role role);
    void update(Role role);
    Role findById(Long id);
    Role findByName(String name);
    Set<Role> findAll();
    Set<String> findAllNames();
    void deleteById(Long id);
}
