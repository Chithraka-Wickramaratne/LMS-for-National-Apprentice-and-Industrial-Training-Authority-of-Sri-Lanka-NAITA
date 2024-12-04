package com.naita.student_lms.firebase;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.Storage.SignUrlOption;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.concurrent.TimeUnit;

@Service
public class FirebaseStorageService {

    private final Storage storage;

    public FirebaseStorageService() {
        this.storage = StorageOptions.getDefaultInstance().getService();
    }

    public String generateSignedUrl(String bucketName, String fileName) {
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName).build();
        URL signedUrl = storage.signUrl(
                blobInfo,
                15,
                TimeUnit.MINUTES,
                SignUrlOption.withV4Signature()
        );
        return signedUrl.toString();
    }
}
