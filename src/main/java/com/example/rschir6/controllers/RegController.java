package com.example.rschir6.controllers;

import com.example.rschir6.models.User;
import com.example.rschir6.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reg")
public class RegController {

    private final UserService userService;
    private final String passAdmin = "root";
    private final String codeVip = "root";

    public RegController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getRegPage()
    {
        return "reg";

    }

    @PostMapping()
    public String regUser(String name, String email, String password, String code)
    {
        User user;
        if(password.equals(this.passAdmin)){
            user = new User(name, email, password, true, true);
        }
        else if(code.equals(codeVip)){
            user = new User(name, email, password, true, false);
        }
        else{
            user = new User(name, email, password, false, false);
        }

        this.userService.save(user);

        return "auth";

    }
}
