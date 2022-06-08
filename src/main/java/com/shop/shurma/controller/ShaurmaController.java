package com.shop.shurma.controller;

import com.shop.shurma.entity.Shaurma;
import com.shop.shurma.service.ShaurmaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Controller
@Slf4j
public class ShaurmaController {
    @Autowired
    private final ShaurmaService shaurmaService;

    public ShaurmaController(ShaurmaService shaurmaService) {
        this.shaurmaService = shaurmaService;
    }

    @GetMapping("/shaurmas")
    public String findAll(Model model) {
        List<Shaurma> shaurmas = shaurmaService.findAll();
        model.addAttribute("shaurmas", shaurmas);
        return "shaurmasList";
    }

    @GetMapping("/home")
    public String findAllforHome(Model model) {
        List<Shaurma> shaurmas2 = shaurmaService.findAll();
        model.addAttribute("shaurmas2", shaurmas2);
        return "/home";
    }

    @GetMapping("/shaurma-create")
    public String createShaurmaForm(Shaurma shaurma) {
        return "shaurma-create";
    }

    @PostMapping("/shaurma-create")
    public String createShaurma(@RequestParam("file1") MultipartFile file1, @RequestParam("file1") MultipartFile file2,
                                @RequestParam("file1") MultipartFile file3,Shaurma shaurma) throws IOException {
        shaurmaService.saveShaurma(shaurma, file1, file2, file3);
        return "redirect:/shaurmas";
    }

    @GetMapping("shaurma-delete/{id}")
    public String deleteShaurma(@PathVariable("id") Long id) {
        shaurmaService.deleteById(id);
        return "redirect:/shaurmas";
    }

    @GetMapping("shaurma-update/{id}")
    public String updateShaurma(@PathVariable("id") Long id, Model model) {
        Shaurma shaurma = shaurmaService.findById(id);
        model.addAttribute("shaurma", shaurma);
        return "/shaurma-update";
    }

    @PostMapping("shaurma-update")
    public String updateShaurma(@RequestParam("file1") MultipartFile file1, @RequestParam("file1") MultipartFile file2,
                                @RequestParam("file1") MultipartFile file3,Shaurma shaurma) throws IOException {
        shaurmaService.saveShaurma(shaurma, file1, file2, file3);
        return "redirect:/shaurmas";
    }


@GetMapping("product/{id}")
public String productPage(@PathVariable("id") Long id, Model model) {
    Shaurma shaurma = shaurmaService.findById(id);
    model.addAttribute("shaurma",shaurma);
    log.info("Was getted shaurma, where id ={} ",id);
    return "/product";

}
//@GetMapping("/navbar")
//    public String findByTitle(String title,Model model) {
//        List<Shaurma> shaurmaFind = shaurmaService.findByTitle(title);
//        model.addAttribute("shaurmaFind",shaurmaFind);
//        return "/";
//}

}

