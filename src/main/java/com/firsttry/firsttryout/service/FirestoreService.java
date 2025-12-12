package com.firsttry.firsttryout.service;

import com.firsttry.firsttryout.model.FirestoreURL;
import com.firsttry.firsttryout.model.Urls;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FirestoreService {

    @Autowired
    private Firestore firestore;

    @Autowired
    private  GeminiService geminiService;

    @Value("${FIRESTORE_COLLECTION_NAME}")
    private String COLLECTION_NAME;

    public String saveUser(String userId, Map<String, Object> user) throws Exception {
        ApiFuture<WriteResult> future =
                firestore.collection(COLLECTION_NAME).document(userId).set(user);

        return future.get().getUpdateTime().toString();
    }

    public Map<String, Object> getUrl(String urlId) throws Exception {
        DocumentSnapshot snapshot =
                firestore.collection(COLLECTION_NAME).document(urlId).get().get();

        if (snapshot.exists()) {
            return snapshot.getData();
        }
        return null;
    }


    public List<Map<String, Object>> getFirstTenUsers() throws Exception {
        ApiFuture<QuerySnapshot> future = firestore
                .collection(COLLECTION_NAME)
                .limit(10)
                .get();

        QuerySnapshot snapshot = future.get();

        List<Map<String, Object>> results = new ArrayList<>();

        for (DocumentSnapshot doc : snapshot.getDocuments()) {
            results.add(doc.getData());
        }

        return results;
    }



    public String addUrl(FirestoreURL url) throws Exception {

        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document();

        ApiFuture<WriteResult> result = docRef.set(url);

        result.get();  // Wait until write completes
        return docRef.getId();
    }


    public String addCustomUrl(String urlDesc, FirestoreURL url) throws Exception{



            ApiFuture<WriteResult> future =
                    firestore.collection(COLLECTION_NAME)
                            .document(urlDesc)
                            .set(url);

            future.get();
            return urlDesc;
    }

}
