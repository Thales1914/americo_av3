package com.seuprojeto.hotel.hotel_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                                "https://hotel-frontend-kxfpqlvps-thales-projects-d8c81c16.vercel.app",
                                "http://localhost:5500",
                                "http://localhost"
                        )
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
}
