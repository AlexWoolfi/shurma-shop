package com.shop.shurma.controller;

import com.shop.shurma.entity.Image;
import com.shop.shurma.repository.ImageRepository;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
public class ImgController {
    private final ImageRepository imageRepository;

    public ImgController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
        @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageByID(@PathVariable("id") Long id ) {
            Image image = imageRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("filename", image.getOriginalName())
                .contentType(MediaType.valueOf(image.getContentType()))
                        .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
}
}
