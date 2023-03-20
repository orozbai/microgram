package com.example.microgram.controller;

import com.example.microgram.dto.SignUpDto;
import com.example.microgram.entity.User;
import com.example.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        //TODO проверить что в базе нету такого пользователя проверка по email и username
        if (userService.checkUserFromAccountName(signUpDto.getUsername()).contains(signUpDto.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        if (userService.checkUserFromEmail(signUpDto.getEmail()).contains(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        //TODO создать объект пользователя
        User user = new User();
        user.setAccountName(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(signUpDto.getPassword());
        //TODO сохранить его в базу

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
