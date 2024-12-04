package com.naita.student_lms.service;
import com.naita.student_lms.entity.Hotel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class HotelService {

    private static final String COLLECTION_NAME = "Hotel";


    public String SaveHotel(Hotel hotel) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        // Add user to Firestore without specifying a document ID (Firestore will generate it)
        DocumentReference docRef = db.collection(COLLECTION_NAME).document();
        hotel.setId(docRef.getId());  // Set the generated document ID as the user's ID

        // Save user with the generated ID
        ApiFuture<WriteResult> collectionApiFuture = docRef.set(hotel);

        // Return the generated ID
        return hotel.getId();
    }

    public List<Hotel> getHotelContent() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        // Query to order documents by timestamp in descending order
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get();

        return future.get().toObjects(Hotel.class);
    }

    // Delete a user by id
    public String DeleteHotel(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = db.collection(COLLECTION_NAME).document(id).delete();
        return "Hotel Course assignment with id " + id + " has been deleted.";
    }

    // Update user details
    public String updateHotel(Hotel hotel) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        if (hotel.getId() == null || hotel.getId().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }

        ApiFuture<WriteResult> collectionApiFuture = db.collection(COLLECTION_NAME).document(hotel.getId()).set(hotel);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

}