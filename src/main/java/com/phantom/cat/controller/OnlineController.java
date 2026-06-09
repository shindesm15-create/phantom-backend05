package com.phantom.cat.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@CrossOrigin(origins = "*")
public class OnlineController {


private static final Set<String> ONLINE_USERS =
        ConcurrentHashMap.newKeySet();

@PostMapping("/online")
public void online(@RequestParam String user) {

    ONLINE_USERS.add(user);
}

@PostMapping("/offline")
public void offline(@RequestParam String user) {

    ONLINE_USERS.remove(user);
}

@GetMapping("/online-users")
public Set<String> getOnlineUsers() {

    return ONLINE_USERS;
}


}
