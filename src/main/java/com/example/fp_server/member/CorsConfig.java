package com.example.fp_server.member;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


/**
 * Denna classen förhindrar webbläsaren att blockera XMLHttpRequest-förfrågan
 * på grund av "CORS"-policy. CORS (Cross-Origin Resource Sharing) är en
 * säkerhetsfunktion som används av webbläsare för att förhindra webbsidor
 * från att göra obehöriga förfrågningar till andra webbplatser.
 * Denna classen gör det möjligt för Spring Boot-applikation
 * att tillåta CORS-requests webbsidan.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/**")
                .allowedOrigins("http://127.0.0.1:5500/") // Lägg till din webbsida här
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}