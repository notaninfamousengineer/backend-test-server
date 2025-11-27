package com.firsttry.firsttryout.service;


import com.firsttry.firsttryout.model.RequestCLCApi;
import com.firsttry.firsttryout.model.RequestSpooApi;
import com.firsttry.firsttryout.model.ResponseCLCApi;
import com.firsttry.firsttryout.model.ResponseSpooApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class AllURLShorteningServices {

    @Autowired
    private WebClient webClient;


    public Flux<ResponseCLCApi> sendPostRequesttoCLC(RequestCLCApi requestDto) {
        //"http://localhost:8080/api/clc/send"
        return webClient.post()
                .uri("https://clc.is/api/links")   // API endpoint
                .bodyValue(requestDto)                     // JSON body
                .retrieve()
                .bodyToFlux(ResponseCLCApi.class);            // Expected response type
    }

    public Flux<ResponseSpooApi> sendPostRequesttoSpoo(RequestSpooApi requestDto) {
        //"http://localhost:8080/api/clc/send"
        return webClient.post()
                .uri("https://spoo.me/api/v1/shorten")   // API endpoint
                .bodyValue(requestDto)                     // JSON body
                .retrieve()
                .bodyToFlux(ResponseSpooApi.class);            // Expected response type
    }
}
