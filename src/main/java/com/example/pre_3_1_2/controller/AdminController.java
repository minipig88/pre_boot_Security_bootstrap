package com.example.pre_3_1_2.controller;

import com.example.pre_3_1_2.model.User;
import com.example.pre_3_1_2.service.RoleService;
import com.example.pre_3_1_2.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes({"userList", "currentUser", "allRoles", "addUser"})
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String printUsers(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        List<User> userList = userService.findAll();
        modelMap.addAttribute("addUser", new User());
        modelMap.addAttribute("userList", userList);
        modelMap.addAttribute("currentUser", currentUser);
        modelMap.addAttribute("allRoles", roleService.findAll());
        return "main";
    }

    @PostMapping(value = "/add")
    public String addUser(User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/edit")
    public String editUser(User user, String[] roles) {
        userService.update(user, roles);
        return "redirect:/admin";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
