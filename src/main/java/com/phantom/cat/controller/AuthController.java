package com.phantom.cat.controller;

import com.phantom.cat.model.User;
import com.phantom.cat.service.CatmService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String result = userService.login(user.getName(), user.getPassword());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.register(user.getName(), user.getPassword());
    }
}