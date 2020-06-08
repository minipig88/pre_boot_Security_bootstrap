package com.example.pre_3_1_2.service;

import com.example.pre_3_1_2.dao.RoleDao;
import com.example.pre_3_1_2.model.Role;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }

    @Override
    public Set<Role> findAll() {
        List<Role> roleList = roleDao.findAll();
        return new HashSet<>(roleList);
    }
}
