package com.example.microgram.controller;

import com.example.microgram.dto.SearchUsersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchUsersController {
    public ResponseEntity<?> searchUsers(@RequestBody SearchUsersDto searchUsersDto) {
        // поиск пользователей по имени, email, имени учетной записи

        return new ResponseEntity<>("User found", HttpStatus.OK);
    }
}
