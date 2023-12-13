package com.example.rschir6.controllers;

import com.example.rschir6.models.User;
import com.example.rschir6.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getFullStaff(Model model)
    {
        model.addAttribute("userlist", userService.getAll());
        return "users";

    }

    @GetMapping("/{id}")
    @ResponseBody
    public User getOne(@PathVariable int id) {
        return userService.getOne(id);
    }

}
