package com.example.microgram.controller;

import com.example.microgram.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sign-up")
public class SignUpController {
    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        //проверить что в базе нету такого пользователя проверка по email и username

        //создать объект пользователя
        //сохранить его
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
