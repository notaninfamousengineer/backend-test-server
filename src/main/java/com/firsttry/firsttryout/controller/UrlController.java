package com.firsttry.firsttryout.controller;


import com.firsttry.firsttryout.model.*;
import com.firsttry.firsttryout.service.AllURLShorteningServices;
import com.firsttry.firsttryout.service.FirestoreService;
import com.firsttry.firsttryout.service.GeminiService;
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
@CrossOrigin(origins = "chrome-extension://miafpdbdgdcnnkobgmhplbepagmncplc")
public class UrlController {


    private final long startTime = System.currentTimeMillis();


    @Autowired
    private AllURLShorteningServices allURLShorteningServices;

    @Autowired
    private FirestoreService firestoreService;

    private String BASE_URL;
    UrlShortenService urlShortenService;

    @Autowired
    private GeminiService geminiService;

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


    private List<LinkDirRequest> linkdirStorage = new ArrayList<>(
            List.of(
                    new LinkDirRequest("First Link Dir", "Sample", new ArrayList<>(List.of("Link1", "Link2")), "user1")
            )
    );

//    @PostMapping("/shorten")
//    public ResponseEntity<Urls> getShortenUrls(@RequestBody Urls urls) {
//
//        int urlID = urlShortenService.getNextURLId();
//        String shortURL = this.BASE_URL + "api/" + Integer.toString(urlID);
//
//        Urls newURLObj = new Urls(urlID, urls.getLongUrl(), shortURL);
//
//        urlStorage.add(newURLObj);
//
//        return ResponseEntity.ok(newURLObj);
//    }


    @GetMapping("/{urlid}")
    public ResponseEntity<Void> getUrl(@PathVariable int urlid) {

        for  (Urls url : urlStorage) {
            if (url.getUrlid() == urlid) {
                return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).header("Location", url.getLongUrl()).build();
            }
        }

        return ResponseEntity.notFound().build();
    }



//    ------------------------------------------------- Actual Implementation --------------------------------

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


    @PostMapping("/shorten")
    public ResponseEntity<Urls> getShortenUrls(@RequestBody FirestoreURL urls) throws Exception {

        String urlid = firestoreService.addUrl(urls);

        Urls url = new Urls(0, urls.getUrl(),"comprl.web.app/"+urlid );

//        urls.setShortUrl("https://backend-test-server-7wsu.onrender.com/"+urlid);

        return ResponseEntity.ok(url);

    }


    @PostMapping("/customshorten")
    public ResponseEntity<Urls> getCustomShortenUrls(@RequestBody FirestoreURL urls) throws Exception {

        String urlDesc = geminiService.getURLDescription(urls.getUrl());

        String urlid = firestoreService.addCustomUrl(urlDesc, urls);

        Urls url = new Urls(0, urls.getUrl(),"comprl.web.app/"+urlid );

        return ResponseEntity.ok(url);

    }


    @PostMapping("/createlinkdir")
    public ResponseEntity<LinkDirRequest> createLinkDir(@RequestBody LinkDirRequest linkDirRequest) {
        linkdirStorage.add(linkDirRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(linkDirRequest);
    }

    @GetMapping("/linkdir/{id}")
    public ResponseEntity<LinkDirRequest> getLinkDir(@PathVariable String id) {

        for  (LinkDirRequest linkDirRequest : linkdirStorage) {
            if(linkDirRequest.getUserId().equals(id)){
                return ResponseEntity.ok(linkDirRequest);
            }
        }

        return ResponseEntity.notFound().build();
    }


    @GetMapping("/health")
    public ResponseEntity<Health> getHealth() {

        return ResponseEntity.ok(new Health("UP", System.currentTimeMillis() - startTime));

    }


    @GetMapping("/urldesc")
    public ResponseEntity<String> getURLDesc(@RequestBody String url){
//        String url = "https://leetcode.com/problems/find-smallest-letter-greater-than-target/description/";
        System.out.println(url);
        String desc = geminiService.getURLDescription(url);

        return ResponseEntity.ok(desc);
    }


    @PostMapping("/short")
    public ResponseEntity<Urls> shortifyURL(@RequestBody HouseShortURLRequest reqUrl) throws Exception {
        String urlid;
        FirestoreURL tempURL = new FirestoreURL(reqUrl.getUrl());
        if(reqUrl.isCustomAISlug()){
            String urlDesc = geminiService.getURLDescription(reqUrl.getUrl());
            urlid = firestoreService.addCustomUrl(urlDesc, tempURL);

        }else{
            urlid = firestoreService.addUrl(tempURL);
//          urls.setShortUrl("https://backend-test-server-7wsu.onrender.com/"+urlid)
        }

        Urls url = new Urls(0, reqUrl.getUrl(),"comprl.web.app/"+urlid );

        return ResponseEntity.ok(url);


    }


}
