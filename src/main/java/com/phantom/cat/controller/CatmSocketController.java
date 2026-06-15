package com.phantom.cat.controller;

import com.phantom.cat.model.Catm;
import com.phantom.cat.service.CatmService;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class CatmSocketController {


private final CatmService service;

public CatmSocketController(CatmService service) {
    this.service = service;
}

// ================= SEND MESSAGE =================

@MessageMapping("/send")
@SendTo("/topic/messages")
public Catm send(Catm message) {

    System.out.println("===== RECEIVED =====");
    System.out.println("content = " + message.getContent());
    System.out.println("imageUrl = " + message.getImageUrl());
    System.out.println("messageType = " + message.getMessageType());

    service.sendMessage(
            message.getFrom(),
            message.getTo(),
            message.getContent(),
            message.getImageUrl(),
            message.getMessageType()
    );

    return message;
}
// ================= TYPING =================

@MessageMapping("/typing")
@SendTo("/topic/typing")
public Catm typing(Catm message) {

    System.out.println(
            "Typing Event => "
                    + message.getFrom()
                    + " -> "
                    + message.getTo()
                    + " : "
                    + message.isTyping()
    );

    return message;
}


}

