package com.example.GettingStartedWithFirebase.service;

import com.example.GettingStartedWithFirebase.model.DemoModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DemoService {

    /**
     * ApiFuture: The ApiFuture is a class provided by the Google Cloud Firestore library used
     * for managing asynchronous operations when interacting with Firestore. In Firestore, many
     * operations, such as reading data, writing data, and querying, are asynchronous, meaning
     * they do not block the execution of your code while waiting for the operation to complete.
     * <p>
     * Instead, these operations return an ApiFuture, which represents a future result that will
     * be available at some point in the future.
     */
    private final String COLLECTION = "demo";

    Firestore dbFirestore = FirestoreClient.getFirestore(); // Get an instance of the database client
    CollectionReference collectionReference = dbFirestore.collection(COLLECTION); // Get collection reference

    public DemoModel deserializeJsonToDemoModel(String json) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(json, DemoModel.class);
        } catch (JsonSyntaxException e) {
            // Handle any deserialization errors appropriately
            e.printStackTrace();
            return null;
        }
    }

    public String addDemo(DemoModel demo) throws ExecutionException, InterruptedException {

        ApiFuture<DocumentReference> apiFuture = collectionReference.add(demo);

        // Get the auto-generated document ID of the newly added document
        DocumentReference newDocument = apiFuture.get();
        return newDocument.getId();
    }

    public List<DemoModel> getAllDemos() throws ExecutionException, InterruptedException {

        List<DemoModel> allDemos = new ArrayList<>();

        // Retrieve all documents in the collection
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = collectionReference.get();
        QuerySnapshot querySnapshot = querySnapshotApiFuture.get();

        // Map each document to a DemoModel object and add it to the list
        for (QueryDocumentSnapshot document : querySnapshot) {
            DemoModel demo = document.toObject(DemoModel.class);
            allDemos.add(demo);
        }

        return allDemos;
    }

    public void updateDemo(String documentId) {
    }

    public void deleteDemo(String documentId) {
    }
}
