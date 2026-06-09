package com.phantom.cat.controller;

import com.phantom.cat.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    // UPLOAD IMAGE
    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        return fileService.uploadImage(file);
    }
}