package com.example.microgram.controller;

import com.example.microgram.dto.LoginDto;
import com.example.microgram.dto.SignUpDto;
import com.example.microgram.entity.User;
import com.example.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/aut/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        try {
            authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok("Авторизация успешна");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Авторизация не удалась", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        if (userService.checkUserFromAccountName(signUpDto.getAccountName()).contains(signUpDto.getAccountName())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        if (userService.checkUserFromEmail(signUpDto.getEmail()).contains(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setName(signUpDto.getName());
        user.setAccountName(signUpDto.getAccountName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(signUpDto.getPassword());
        userService.saveToBase(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
