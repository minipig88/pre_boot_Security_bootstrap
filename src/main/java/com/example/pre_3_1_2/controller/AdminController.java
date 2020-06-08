package com.example.pre_3_1_2.controller;

import com.example.pre_3_1_2.model.User;
import com.example.pre_3_1_2.service.RoleService;
import com.example.pre_3_1_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    //Код стайл. Писать как константу или нет?
    private final UserService userService;
    private final RoleService roleService;
    private List<User> userList;
    private User currentUser;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String printUsers(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        currentUser = (User) authentication.getPrincipal();
        userList = userService.findAll();

        modelMap.addAttribute("userList", userList);
        modelMap.addAttribute("currentUser", currentUser);
        modelMap.addAttribute("showModalDelete", false);
        modelMap.addAttribute("showModalEdit", false);
        modelMap.addAttribute("showTabNew", false);
        modelMap.addAttribute("allRoles", roleService.findAll());
        return "main";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAdd(ModelMap modelMap) {
        modelMap.addAttribute("showTabNew", true);
        modelMap.addAttribute("addUser", new User());
        modelMap.addAttribute("currentUser", currentUser);
        modelMap.addAttribute("allRoles", roleService.findAll());
        return "main";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String showEdit(@RequestParam(value = "id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("showModalEdit", true);
        modelMap.addAttribute("userList", userList);
        modelMap.addAttribute("userById", userService.findById(id));
        modelMap.addAttribute("currentUser", currentUser);
        modelMap.addAttribute("allRoles", roleService.findAll());
        return "main";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String showDelete(@RequestParam(value = "id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("showModalDelete", true);
        modelMap.addAttribute("userList", userList);
        modelMap.addAttribute("userById", userService.findById(id));
        modelMap.addAttribute("currentUser", currentUser);
        modelMap.addAttribute("allRoles", roleService.findAll());
        return "main";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteUser(User user) {
        userService.deleteById(user.getId());
        return "redirect:/admin";
    }

}
