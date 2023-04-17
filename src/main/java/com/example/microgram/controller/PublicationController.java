package com.example.microgram.controller;

import com.example.microgram.dto.CommentDto;
import com.example.microgram.entity.Publication;
import com.example.microgram.security.SecurityConfig;
import com.example.microgram.service.CommentService;
import com.example.microgram.service.PublicationService;
import com.example.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PublicationController {
    @Autowired
    private PublicationService publicationService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @GetMapping("/publications/")
    public String showPublications(Model model) {
        List<Publication> publications = publicationService.getPublicationOfOtherUsers(SecurityConfig.getCurrentUserEmail());
        model.addAttribute("publications", publications);
        return "publications";
    }

//    @GetMapping("/watch")
//    public String showAllPosts(Model model, Authentication authentication) {
//        List<Publication> publications = publicationService.selectAllPublications();
//        model.addAttribute("publications", publications);
//        var user2 = (UserDetails) authentication.getPrincipal();
//        var user = userService.getUserFromEmail(user2.getUsername());
//        Long postId = 1 + publicationService.getPostLastId();
//        int currentUserId = user.get(0).getId();
//        model.addAttribute("postId", postId);
//        model.addAttribute("userId", currentUserId);
//        return "index";
//    }

    @GetMapping("/publications/{id}")
    public String showPublicationById(@PathVariable Long id, Model model) {
        List<Publication> publications = publicationService.getPublicationById(id);
        model.addAttribute("publications", publications);
        return "publications";
    }
    //запустить сервер и нажать создать положив картинку
    @PostMapping("/publications/add")
    public String addPost(@RequestParam(name = "imageLink") MultipartFile file, Model model,
                          @RequestParam(name = "description", required = false) String description,
                          @RequestParam(name = "user_id", required = false) Integer userId,
                          Authentication authentication) {
        byte[] data;
        String fileName = file.getOriginalFilename();
        Path path = Paths.get("src/main/resources/static/images/" + fileName);
        var user = (UserDetails) authentication.getPrincipal();
        var id = userService.getUserFromEmail(user.getUsername()).get(0).getId();
        try {
            data = file.getBytes();
            Files.write(path, data);
            publicationService.addPublication(fileName, description, id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }

    @DeleteMapping("/publications/{id}")
    public ResponseEntity<Void> deletePublicationById(@PathVariable Long id) {
        if (publicationService.deletePublicationById(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/publications/comment")
    public String addCommentToPost(@RequestParam(name = "userId", required = false) Long userId,
                                   @RequestParam(name = "postId", required = false) Long postId,
                                   @RequestParam(name = "commentText", required = false) String text) {
        commentService.addComment(userId, postId, text);
        return "index";
    }

    @GetMapping("/get-id")
    public Long getId() {
        return 1 + publicationService.getPostLastId();
    }

    @DeleteMapping("/publications/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        if (publicationService.deleteComment(commentId))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/publications/{postId}/like/{userId}/")
    public String likePublication(@PathVariable Long userId, @PathVariable Long postId) {
        if (publicationService.likePublication(userId, postId)) {
            return "liked";
        } else {
            return "You already liked";
        }
    }
}
