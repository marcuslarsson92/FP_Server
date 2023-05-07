package com.example.fp_server.member.Controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Loginrequest {
    private String email;
    private String password;
    private Member member;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Member getMember(){
        return member;
    }

    public void setMember (Member member){
        this.member = member;
    }

    public String encryptPassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

}
