package com.naita.student_lms.service;
import com.naita.student_lms.entity.Software;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class SoftwareService {

    private static final String COLLECTION_NAME = "Software";


    // Save user method (unchanged)
    public String SaveSoftware(Software software) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection(COLLECTION_NAME).document();
        software.setId(docRef.getId());  // Set the generated document ID as the user's ID

        ApiFuture<WriteResult> collectionApiFuture = docRef.set(software);

        return software.getId();
    }

    public List<Software> getSoftwareContent() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get();

        return future.get().toObjects(Software.class);
    }

    public String DeleteSoftware(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = db.collection(COLLECTION_NAME).document(id).delete();
        return "Software Engineering assignment with id " + id + " has been deleted.";
    }

    public String updateSoftware(Software software) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        if (software.getId() == null || software.getId().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }

        ApiFuture<WriteResult> collectionApiFuture = db.collection(COLLECTION_NAME).document(software.getId()).set(software);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

}