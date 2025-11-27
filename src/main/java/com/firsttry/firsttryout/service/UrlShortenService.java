package com.firsttry.firsttryout.service;


import org.springframework.stereotype.Service;

@Service
public class UrlShortenService {

    private int counter;

    public UrlShortenService() {
        counter = 5;
    }


    public int getNextURLId(){
        return counter++;
    }

}
