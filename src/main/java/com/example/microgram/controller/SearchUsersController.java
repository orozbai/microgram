package com.example.microgram.controller;

import com.example.microgram.dto.SearchUsersDto;
import com.example.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchUsersController {
    @Autowired
    private UserService userService;

    @PostMapping("/search")
    public ResponseEntity<?> searchUsers(@RequestBody SearchUsersDto searchUsersDto) {
        var listName = userService.getUsersFromName(searchUsersDto.getName());
        var listAccountName = userService.getUserFromAccountName(searchUsersDto.getUsername());
        var listEmail = userService.getUserFromEmail(searchUsersDto.getEmail());
        if (listName.size() != 0) {
            return new ResponseEntity<>("User found: " + listName, HttpStatus.OK);
        } else if (listAccountName.size() != 0) {
            return new ResponseEntity<>("User found: " + listAccountName, HttpStatus.OK);
        } else if (listEmail.size() != 0) {
            return new ResponseEntity<>("User found: " + listEmail, HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.OK);
    }
}
