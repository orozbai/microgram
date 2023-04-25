package com.example.microgram.controller;

import com.example.microgram.dto.CommentDto;
import com.example.microgram.entity.Publication;
import com.example.microgram.security.SecurityConfig;
import com.example.microgram.service.CommentService;
import com.example.microgram.service.PublicationService;
import com.example.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PublicationController {
    private final PublicationService publicationService;
    private final CommentService commentService;
    private final UserService userService;

    @GetMapping("/publications/")
    public String showPublications(Model model) {
        List<Publication> publications = publicationService.getPublicationOfOtherUsers(SecurityConfig.getCurrentUserEmail());
        model.addAttribute("publications", publications);
        return "publications";
    }

    @GetMapping("/watch")
    public ResponseEntity<List<Publication>> showAllPosts() {
        List<Publication> publications = publicationService.selectAllPublications();
        return ResponseEntity.ok(publications);
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> commentsAll() {
        List<CommentDto> comments = commentService.showCommentsFromPublication();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/publications/{id}")
    public String showPublicationById(@PathVariable Long id, Model model) {
        List<Publication> publications = publicationService.getPublicationById(id);
        model.addAttribute("publications", publications);
        return "publications";
    }

    @PostMapping("/publications/add")
    public String addPost(@RequestParam(name = "imageLink") MultipartFile file,
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
    public String addCommentToPost(@RequestParam(name = "user_id", required = false) Object user,
                                   @RequestParam(name = "postId", required = false) Object post,
                                   @RequestParam(name = "commentText", required = false) String text) {
        try {
            NumberFormat format = NumberFormat.getInstance();
            Number userId = format.parse((String) user);
            Number postId = format.parse((String) post);
            commentService.addComment(userId.longValue(), postId.longValue(), text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping("/publications/get-id")
    public ResponseEntity<List<Long>> getId(Authentication authentication) {
        List<Long> list = new ArrayList<>();
        var userC = (UserDetails) authentication.getPrincipal();
        var user = userService.getUserFromEmail(userC.getUsername()).get(0).getId();
        Long id = (long) user;
        Long lastId = publicationService.getPostLastId() + 1;
        list.add(id);
        list.add(lastId);
        return ResponseEntity.ok(list);
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
