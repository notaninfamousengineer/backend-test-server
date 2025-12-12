package com.firsttry.firsttryout.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;



@Service
public class GeminiService {

    @Autowired
    private Client client;

    public String getURLDescription(String url){

        String prompt = """
                Generate EXACTLY three words that describe this URL.
                Words spilt by hypen. No explanation.
                URL: """ + url;
        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        prompt,
                        null);

//        System.out.println(response.text());
        return response.text();
    }

}
