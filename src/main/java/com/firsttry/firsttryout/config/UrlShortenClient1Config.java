package com.firsttry.firsttryout.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class UrlShortenClient1Config {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .build();
    }
}
