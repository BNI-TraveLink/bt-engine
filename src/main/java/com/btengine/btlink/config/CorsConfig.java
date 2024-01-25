package com.btengine.btlink.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Atur konfigurasi CORS sesuai kebutuhan
        config.addAllowedOrigin("http://192.168.132.78:4200"); // Sesuaikan dengan alamat Angular Anda
//        config.addAllowedOrigin("*"); // Sesuaikan dengan alamat Angular Anda
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");

        source.registerCorsConfiguration("/", config);

        return new CorsFilter(source);
    }

}