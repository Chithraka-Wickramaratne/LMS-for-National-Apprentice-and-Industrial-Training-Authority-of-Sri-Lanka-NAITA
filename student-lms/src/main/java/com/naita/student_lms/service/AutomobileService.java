package com.naita.student_lms.service;

import com.naita.student_lms.entity.Automobile;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AutomobileService {
    private static final String COLLECTION_NAME = "Automobile";


    public String SaveAutomobile(Automobile automobile) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection(COLLECTION_NAME).document();
        automobile.setId(docRef.getId());

        ApiFuture<WriteResult> collectionApiFuture = docRef.set(automobile);

        return automobile.getId();
    }

    public List<Automobile> getAutomobileContent() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get();

        return future.get().toObjects(Automobile.class);
    }

    public String DeleteAutomobile(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = db.collection(COLLECTION_NAME).document(id).delete();
        return "Automobile Course assignment with id " + id + " has been deleted.";
    }

    public String updateAutomobile(Automobile automobile) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        if (automobile.getId() == null || automobile.getId().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }

        ApiFuture<WriteResult> collectionApiFuture = db.collection(COLLECTION_NAME).document(automobile.getId()).set(automobile);

        return collectionApiFuture.get().getUpdateTime().toString();
    }
}
