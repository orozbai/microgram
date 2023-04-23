package com.example.microgram.dto;

import lombok.Data;

@Data
public class SignUpDto {
    private String name;
    private String accountName;
    private String email;
    private String password;
}
