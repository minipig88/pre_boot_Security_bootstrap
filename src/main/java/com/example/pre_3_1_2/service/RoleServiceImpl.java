package com.example.pre_3_1_2.service;

import com.example.pre_3_1_2.dao.RoleDao;
import com.example.pre_3_1_2.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public Role findById(Long id) {
        return roleDao.findById(id);
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

    @Override
    public Set<String> findAllNames() {
        Set<Role> roleSet = findAll();
        Set<String> roleNames = new HashSet<>();
        for (Role role : roleSet) {
            roleNames.add(role.getRoleName());
        }
        return roleNames;
    }

    @Override
    public void deleteById(Long id) {
        roleDao.deleteById(id);
    }
}
