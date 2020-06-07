package com.example.pre_3_1_2.dao;

import com.example.pre_3_1_2.model.Role;

import java.util.List;

public interface RoleDao {
    void save(Role role);
    void update(Role role);
    Role findById(Long id);
    Role findByName(String name);
    List<Role> findAll();
    void deleteById(Long id);
}
