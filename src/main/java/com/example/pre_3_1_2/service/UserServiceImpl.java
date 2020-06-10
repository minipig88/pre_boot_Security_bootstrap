package com.example.pre_3_1_2.service;

import com.example.pre_3_1_2.dao.RoleDao;
import com.example.pre_3_1_2.dao.UserDao;
import com.example.pre_3_1_2.model.Role;
import com.example.pre_3_1_2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roleSet = new HashSet<>();
        for (Role role : user.getRoles()) {
            roleSet.add(roleDao.findByName(role.getRoleName()));
        }
        user.setRoles(roleSet);
        userDao.save(user);
    }

    @Override
    public void update(User user, String[] roles) {
        User userUpd = userDao.findById(user.getId());
        if (user.getPassword() != null && !user.getPassword().equals("")) {
            userUpd.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(roleDao.findByName(role));
        }
        userUpd.setRoles(roleSet);
        userUpd.setUsername(user.getUsername());
        userUpd.setFirstName(user.getFirstName());
        userUpd.setLastName(user.getLastName());
        userUpd.setAge(user.getAge());
        userDao.update(userUpd);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }
}
