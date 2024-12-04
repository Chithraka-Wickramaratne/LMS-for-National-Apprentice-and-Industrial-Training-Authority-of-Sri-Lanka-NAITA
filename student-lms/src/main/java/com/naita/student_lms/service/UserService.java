package com.naita.student_lms.service;

import com.naita.student_lms.entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private static final String COLLECTION_NAME = "users";

    public String SaveUser(User user) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection(COLLECTION_NAME).document();
        user.setId(docRef.getId());  // Set the generated document ID as the user's ID

        ApiFuture<WriteResult> collectionApiFuture = docRef.set(user);

        return user.getId();
    }

    public User authenticateUser(String email, String password) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> query = db.collection(COLLECTION_NAME).whereEqualTo("email", email).get();
        List<QueryDocumentSnapshot> documents = query.get().getDocuments();

        if (!documents.isEmpty()) {
            User user = documents.get(0).toObject(User.class);  // Assuming the first result is the correct user

            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<User> users = future.get().toObjects(User.class);
        return users;
    }

    public String deleteUser(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = db.collection(COLLECTION_NAME).document(id).delete();
        return "User with email " + id + " has been deleted.";
    }

    public String updateUser(User user) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        if (user.getId() == null || user.getId().isEmpty()) {
            throw new IllegalArgumentException("User email cannot be null or empty");
        }

        ApiFuture<WriteResult> collectionApiFuture = db.collection(COLLECTION_NAME).document(user.getId()).set(user);

        return collectionApiFuture.get().getUpdateTime().toString();
    }


}
