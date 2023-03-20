package com.example.microgram.controller;

import com.example.microgram.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    @PostMapping
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        //TODO аунтификация user

        return new ResponseEntity<>("User signed-in successfully", HttpStatus.OK);
    }
}
