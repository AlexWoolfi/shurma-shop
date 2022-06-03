package com.shop.shurma.controller;

import com.shop.shurma.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImgController {
    private final ImageRepository imageRepository;

//    @GetMapping()
//    private ResponceEntity<?>
}
