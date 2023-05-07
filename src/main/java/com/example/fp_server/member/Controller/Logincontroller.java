package com.example.fp_server.member.Controller;

import com.example.fp_server.member.Member;
import com.example.fp_server.member.Loginrequest;
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

        if (validateCredentials(loginRequest.getEmail(), encryptedPassword)) {
            return loginRequest.getMember();
        } else {
            return null; // Returnera null för att indikera att inlognigen misslyckades
        }
    }

    private boolean validateCredentials(String email, String encryptedPassword) {
       // här ska eposten och lösenordet kontrolleras mot databasen

        return email.equals(storedEmail) && encryptedPassword.equals(storedEncryptedPassword);
    }
}





