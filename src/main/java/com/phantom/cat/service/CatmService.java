package com.phantom.cat.service;

import com.phantom.cat.model.User;
import com.phantom.cat.model.Catm;
import com.phantom.cat.repository.UserRepository;
import com.phantom.cat.repository.CatmRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatmService {

private final UserRepository userRepo;
private final CatmRepository catmRepo;

public CatmService(
        UserRepository userRepo,
        CatmRepository catmRepo) {

    this.userRepo = userRepo;
    this.catmRepo = catmRepo;
}

// ================= REGISTER =================

public String createUser(String name, String password) {

    if (name == null || password == null) {
        return "Invalid data";
    }

    User exist = userRepo.findByName(name);

    if (exist != null) {
        return "User already exists";
    }

    User user = new User();

    user.setName(name);
    user.setPassword(password);

    userRepo.save(user);

    return "User created";
}

// ================= LOGIN =================

public String login(String name, String password) {

    try {

        User user = userRepo.findByName(name);

        if (user == null) {
            return "User not found";
        }

        String dbPass = user.getPassword();

        if (dbPass == null) {
            return "Password not set in DB";
        }

        if (!dbPass.equals(password)) {
            return "Wrong password";
        }

        return "success";

    } catch (Exception e) {

        e.printStackTrace();
        return "server error: " + e.getMessage();
    }
}

// ================= SEND MESSAGE =================

public String sendMessage(
        String from,
        String to,
        String content,
        String imageUrl,
        String messageType) {

    if (from == null || to == null) {
        return "Invalid message";
    }

    Catm msg = new Catm();

    msg.setFrom(from);
    msg.setTo(to);

    msg.setContent(content);
    msg.setImageUrl(imageUrl);

    if (messageType == null) {
        messageType = "TEXT";
    }

    msg.setMessageType(messageType);

    msg.setStatus("sent");
    msg.setTemp(true);

    msg.setTimestamp(System.currentTimeMillis());
    msg.setSeenTime(0);

    catmRepo.save(msg);

    return "sent";
}


        catmRepo.save(msg);

System.out.println(
    "MESSAGE SAVED => "
    + from + " -> "
    + to + " | "
    + messageType
);

        
// ================= MARK AS SEEN =================

public void seenCatm(String from, String to) {

    List<Catm> all = catmRepo.findAll();

    for (Catm m : all) {

        boolean match =
                m.getFrom().equals(from)
                && m.getTo().equals(to);

        if (match) {

            m.setStatus("seen");
            m.setSeenTime(System.currentTimeMillis());

            catmRepo.save(m);
        }
    }
}

// ================= GET CHAT =================

public List<Catm> getMessages(
        String user1,
        String user2) {

    List<Catm> all =
            catmRepo.findConversation(
                    user1,
                    user2
            );

    long now = System.currentTimeMillis();
    long twentyFourHours = 24L * 60 * 60 * 1000;

    List<Catm> result = new ArrayList<>();

    for (Catm m : all) {

        if (m.isTemp()) {

            long age = now - m.getTimestamp();

            if (age >= twentyFourHours) {
                catmRepo.delete(m);
                continue;
            }
        }

        if (m.getMessageType() == null) {
            m.setMessageType("TEXT");
        }

        result.add(m);
    }

    return result;
}

// ================= ALL USERS =================

public List<String> getAllUsers() {

    List<User> all = userRepo.findAll();

    List<String> names = new ArrayList<>();

    for (User u : all) {
        names.add(u.getName());
    }

    return names;
}

// ================= IMAGE UPLOAD =================

public String uploadImage(MultipartFile file)
        throws IOException {

    String uploadDir = "uploads";

    Path uploadPath = Paths.get(uploadDir);

    if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
    }

    String fileName =
            System.currentTimeMillis()
            + "_"
            + file.getOriginalFilename();

    Path filePath =
            uploadPath.resolve(fileName);

    Files.copy(
            file.getInputStream(),
            filePath,
            StandardCopyOption.REPLACE_EXISTING
    );

    return "/uploads/" + fileName;
}

}
