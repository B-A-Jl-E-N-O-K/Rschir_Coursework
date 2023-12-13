package com.example.rschir6.controllers;

import com.example.rschir6.services.DishService;
import com.example.rschir6.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    static boolean isAdmin = false;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAuthPage()
    {
        return "auth";

    }

    @PostMapping()
    public String loginUser(String name, String password, Model model)
    {
        boolean result[] = this.userService.login(name, password);
        if(result[0]){
            isAdmin = result[1];
            return "menu";
        }
        else{
            model.addAttribute("message", "Логин или пароль не верны");
            return "auth";
        }


    }
}
