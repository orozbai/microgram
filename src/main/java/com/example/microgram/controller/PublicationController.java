package com.example.microgram.controller;

import com.example.microgram.dto.CommentDto;
import com.example.microgram.dto.PublicationDto;
import com.example.microgram.entity.Publication;
import com.example.microgram.security.SecurityConfig;
import com.example.microgram.service.CommentService;
import com.example.microgram.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PublicationController {
    @Autowired
    private PublicationService publicationService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/publications/")
    public String showPublications(Model model) {
        List<Publication> publications = publicationService.getPublicationOfOtherUsers(SecurityConfig.getCurrentUserEmail());
        model.addAttribute("publications", publications);
        return "publications";
    }

    @GetMapping("/watch")
    public String showAllPosts(Model model) {
        List<Publication> publications = publicationService.selectAllPublications();
        model.addAttribute("publications", publications);
        return "publications";
    }

    @GetMapping("/publications/{id}")
    public String showPublicationById(@PathVariable Long id, Model model) {
        List<Publication> publications = publicationService.getPublicationById(id);
        model.addAttribute("publications", publications);
        return "publications";
    }

    @PostMapping("/publications/add")
    public PublicationDto addPublication(@RequestBody PublicationDto publicationDto, @RequestParam("imageLink") MultipartFile file) {
        System.out.println(file);
        return publicationService.addPublication(publicationDto);
    }

    @DeleteMapping("/publications/{id}")
    public ResponseEntity<Void> deletePublicationById(@PathVariable Long id) {
        if (publicationService.deletePublicationById(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/publications/{id}/comment")
    public CommentDto addCommentToPost(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        return commentService.addComment(id, commentDto);
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
