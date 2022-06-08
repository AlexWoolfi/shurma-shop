package com.shop.shurma.service;

import com.shop.shurma.entity.Image;
import com.shop.shurma.entity.Shaurma;
import com.shop.shurma.repository.ShaurmaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class ShaurmaService {
    private final ShaurmaRepository shaurmaRepository;
    @Autowired
    public ShaurmaService(ShaurmaRepository shaurmaRepository) {
        this.shaurmaRepository = shaurmaRepository;
    }

    public Shaurma findById(Long id) {
        return shaurmaRepository.findById(id).orElse(null);
    }

    public List<Shaurma> findAll() {
        return shaurmaRepository.findAll();
    }

    public Shaurma saveShaurma(Shaurma shaurma, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Image image1;
        Image image2;
        Image image3;
        if(file1.getSize() !=0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            shaurma.imageToShaurma(image1);
        }
        if(file2.getSize() !=0) {
            image2 = toImageEntity(file2);
            shaurma.imageToShaurma(image2);
        }
        if(file1.getSize() !=0) {
            image3 = toImageEntity(file3);
            shaurma.imageToShaurma(image3);
        }
        Shaurma shaurmaFromDb = shaurmaRepository.save(shaurma);
        shaurmaFromDb.setPreviewImgId(shaurmaFromDb.getImages().get(0).getId());
        return shaurmaRepository.save(shaurma);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteById(Long id) {
        shaurmaRepository.deleteById(id);
    }





}
