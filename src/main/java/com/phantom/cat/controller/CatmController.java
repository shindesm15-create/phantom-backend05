package com.phantom.cat.controller;

import com.phantom.cat.model.Catm;
import com.phantom.cat.service.ChatService;
import com.phantom.cat.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatmController {

    private final ChatService chatService;
    private final UserService userService;

    public CatmController(ChatService chatService,
                          UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    // GET CHAT
    @GetMapping("/messages")
    public List<Catm> getMessages(@RequestParam String user1,
                                  @RequestParam String user2) {
        return chatService.getChat(user1, user2);
    }

    // USERS LIST
    @GetMapping("/users")
    public List<String> getUsers() {
        return userService.getAllUsers();
    }

    // MARK AS SEEN
    @PostMapping("/seen")
    public void seen(@RequestParam String from,
                     @RequestParam String to) {
        chatService.markSeen(from, to);
    }
}