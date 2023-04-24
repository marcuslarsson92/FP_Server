package com.example.fp_server.member.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Logincontroller {
    @PostMapping("/login")
    public String loginUser(@RequestBody Loginrequest loginRequest) {
        String encryptedPassword = loginRequest.encryptPassword();
        System.out.println(loginRequest.getEmail());
        System.out.println(encryptedPassword);
        return "Login successful";
    }
}
