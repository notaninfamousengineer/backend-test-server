package com.firsttry.firsttryout.service;


import com.firsttry.firsttryout.model.RequestCLCApi;
import com.firsttry.firsttryout.model.ResponseCLCApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;

@Service
public class URLShorteningCLC {

    @Autowired
    private WebClient webClient;

    public Flux<ResponseCLCApi> sendPostRequest(RequestCLCApi requestDto) {
//"http://localhost:8080/api/clc/send"
        return webClient.post()
                .uri("https://clc.is/api/links")   // API endpoint
                .bodyValue(requestDto)                     // JSON body
                .retrieve()
                .bodyToFlux(ResponseCLCApi.class);            // Expected response type
    }


}
