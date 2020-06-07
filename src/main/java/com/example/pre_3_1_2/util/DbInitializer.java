package com.example.pre_3_1_2.util;

import com.example.pre_3_1_2.model.Role;
import com.example.pre_3_1_2.model.User;
import com.example.pre_3_1_2.service.RoleService;
import com.example.pre_3_1_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DbInitializer {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public DbInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        initRepo();
    }

    public void initRepo() {
        roleService.save(new Role("ADMIN"));
        roleService.save(new Role("USER"));

        Set<Role> rolesUser = new HashSet<>();
        rolesUser.add(roleService.findByName("USER"));

        Set<Role> rolesAdmin = new HashSet<>();
        rolesAdmin.add(roleService.findByName("ADMIN"));
        rolesAdmin.add(roleService.findByName("USER"));

        userService.save(new User("admin@gmail.com", "1",
                "admin", "admin", 35, rolesAdmin));
        userService.save(new User("user@gmail.com", "1",
                "user", "user", 27, rolesUser));
    }
}
