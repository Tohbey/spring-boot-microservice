package com.example.accountmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account-management")
public class AccountManagement {
    @GetMapping("/status/check")
    public String status(){
        return "Working";
    }
}
