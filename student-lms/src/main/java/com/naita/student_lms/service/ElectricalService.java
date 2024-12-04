package com.naita.student_lms.service;

import com.naita.student_lms.entity.Electrical;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ElectricalService {

    private static final String COLLECTION_NAME = "Electrical";


    public String SaveElectrical(Electrical electrical) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection(COLLECTION_NAME).document();
        electrical.setId(docRef.getId());  // Set the generated document ID as the user's ID

        ApiFuture<WriteResult> collectionApiFuture = docRef.set(electrical);

        return electrical.getId();
    }

    public List<Electrical> getElectricalContent() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get();

        return future.get().toObjects(Electrical.class);
    }

    public String DeleteElectrical(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = db.collection(COLLECTION_NAME).document(id).delete();
        return "Hotel Course assignment with id " + id + " has been deleted.";
    }

    public String updateElectrical(Electrical electrical) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        if (electrical.getId() == null || electrical.getId().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }

        ApiFuture<WriteResult> collectionApiFuture = db.collection(COLLECTION_NAME).document(electrical.getId()).set(electrical);

        return collectionApiFuture.get().getUpdateTime().toString();
    }
}
