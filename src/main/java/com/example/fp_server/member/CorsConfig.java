package com.example.fp_server.member;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


/**
 * This class prevents the browser from blocking an XMLHttpRequest
 * request due to the "CORS" policy. CORS (Cross-Origin Resource Sharing)
 * is a security feature used by browsers to prevent web pages from making
 * unauthorized requests to other websites.
 * @author marcus
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