package com.example.microgram.controller;

import com.example.microgram.entity.Publication;
import com.example.microgram.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PublicationController {
    @Autowired
    private PublicationService publicationService;

    @GetMapping("/publications/{userId}")
    public String showPublications(@PathVariable Long userId, Model model) {
        List<Publication> publications = publicationService.getPublicationOfOtherUsers(userId);
        model.addAttribute("publications", publications);
        return "publications";
    }

    //TODO сделать метод добавление новой публикации

    //TODO сделать метод удаление своей публикации

    //TODO сделать метод добавление коментариев к публикации

    //TODO сделать метод 'мне понравилось' на публикации

}
