package com.example.pre_3_1_2.service;

import com.example.pre_3_1_2.model.Role;

import java.util.Set;

public interface RoleService {
    void save(Role role);
    Role findByName(String name);
    Set<Role> findAll();
}
