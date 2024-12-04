package com.naita.student_lms.service;
import com.naita.student_lms.entity.Ict;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class IctService {

    private static final String COLLECTION_NAME = "ICT";


    // Save user method (unchanged)
    public String SaveIct(Ict ict) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection(COLLECTION_NAME).document();
        ict.setId(docRef.getId());  // Set the generated document ID as the user's ID

        ApiFuture<WriteResult> collectionApiFuture = docRef.set(ict);

        return ict.getId();
    }

    public List<Ict> getIctContent() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get();

        return future.get().toObjects(Ict.class);
    }

    public String DeleteIct(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = db.collection(COLLECTION_NAME).document(id).delete();
        return "Ict assignment with id " + id + " has been deleted.";
    }

    public String updateIct(Ict ict) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        if (ict.getId() == null || ict.getId().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }

        ApiFuture<WriteResult> collectionApiFuture = db.collection(COLLECTION_NAME).document(ict.getId()).set(ict);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

}
