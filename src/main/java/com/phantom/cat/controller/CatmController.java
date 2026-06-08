package com.phantom.cat.controller;

import com.phantom.cat.model.Catm;
import com.phantom.cat.service.CatmService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class CatmController {

    private final CatmService service;

    public CatmController(CatmService service) {
        this.service = service;
    }

    @GetMapping("/messages")
    public List<Catm> getMessages(@RequestParam String user1,
                                  @RequestParam String user2) {
        return service.getMessages(user1, user2);
    }

    @GetMapping("/users")
    public List<String> getUsers() {
        return service.getAllUsers();
    }

    @PostMapping("/seen")
    public void seen(@RequestParam String from,
                     @RequestParam String to) {
        service.seenCatm(from, to);
    }

    ;
}
}
