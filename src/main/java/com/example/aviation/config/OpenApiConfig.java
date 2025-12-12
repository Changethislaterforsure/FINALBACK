package com.example.aviation.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(
                new Info()
                        .title("Aviation API")
                        .version("1.0")
                        .description("Arrivals, departures, and airport administration API")
        );
    }
}
