package com.example.microgram.controller;

import com.example.microgram.entity.Customer;
import com.example.microgram.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final HomeService service;

    @GetMapping("/connect")
    public ResponseEntity<String> connection() {
        return new ResponseEntity<>(service.connect(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create() {
        return new ResponseEntity<>(service.shouldCreatTable(), HttpStatus.OK);
    }

    @GetMapping("/select")
    public ResponseEntity<String> select() {
        return new ResponseEntity<>(service.shouldSelectData(), HttpStatus.OK);
    }

    @GetMapping("/hikari")
    public ResponseEntity<String> hikari() {
        return new ResponseEntity<>(service.getDataSourceConnection(), HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<>(service.getCustomers(), HttpStatus.OK);
    }
}
