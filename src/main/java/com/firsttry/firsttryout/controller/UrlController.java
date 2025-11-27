package com.firsttry.firsttryout.controller;


import com.firsttry.firsttryout.model.*;
import com.firsttry.firsttryout.service.AllURLShorteningServices;
import com.firsttry.firsttryout.service.FirestoreService;
import com.firsttry.firsttryout.service.UrlShortenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "chrome-extension://ghoieckkcpdepdfibkabjdpbokpdodgk")
public class UrlController {


    @Autowired
    private AllURLShorteningServices allURLShorteningServices;

    @Autowired
    private FirestoreService firestoreService;

    private String BASE_URL;
    UrlShortenService urlShortenService;

    public UrlController() {
        BASE_URL = "http://localhost:8080/";
        urlShortenService = new UrlShortenService();
    }

    private List<Urls> urlStorage = new ArrayList<>(
            List.of(
                    new Urls(1, "Loooooonnggg1.url", "short1.url"),
                    new Urls(2, "Loooooonnggg2.url", "short2.url"),
                    new Urls(3, "Loooooonnggg3.url", "short3.url"),
                    new Urls(4, "Loooooonnggg4.url", "short4.url")
            )
    );


    @PostMapping("/shorten")
    public ResponseEntity<Urls> getShortenUrls(@RequestBody Urls urls) {

        int urlID = urlShortenService.getNextURLId();
        String shortURL = this.BASE_URL + "api/" + Integer.toString(urlID);

        Urls newURLObj = new Urls(urlID, urls.getLongUrl(), shortURL);

        urlStorage.add(newURLObj);

        return ResponseEntity.ok(newURLObj);
    }


    @GetMapping("/{urlid}")
    public ResponseEntity<Void> getUrl(@PathVariable int urlid) {

        for  (Urls url : urlStorage) {
            if (url.getUrlid() == urlid) {
                return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).header("Location", url.getLongUrl()).build();
            }
        }

        return ResponseEntity.notFound().build();
    }



    @PostMapping("/clc/shorten")
    public Mono<ResponseCLCApi> shorten(@RequestBody RequestCLCApi dto) {
        return allURLShorteningServices.sendPostRequesttoCLC(dto).elementAt(0);
    }

    @PostMapping("/clc/send")
    public Flux<ResponseCLCApi> send(@RequestBody RequestCLCApi dto) {

//        ResponseCLCApi newResponse = new ResponseCLCApi(dto, "/yeahGotItMan", "localhost:8080/yeahGotItMan", true);
        ResponseCLCApi newResponse = new ResponseCLCApi(new RequestCLCApi("vlv.id", "thisisloooongurl"), "slug", "shorturl", true);
        return Flux.just(newResponse);
    }


    @PostMapping("/spoo/shorten")
    public Mono<ResponseSpooApi> spooShorten(@RequestBody RequestSpooApi dto) {
        return allURLShorteningServices.sendPostRequesttoSpoo(dto).elementAt(0);
    }


    @GetMapping("/l/all")
    public ResponseEntity<List<Map<String, Object>>> getAllUrlItems() throws Exception {

        return  ResponseEntity.ok(firestoreService.getFirstTenUsers());

    }


    @GetMapping("/l/{id}")
    public ResponseEntity<Map<String, Object>> getUrlById(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(firestoreService.getUrl(id));
    }


}
