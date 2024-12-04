package com.naita.student_lms.controller;

import com.naita.student_lms.firebase.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @GetMapping("/get-signed-url")
    public String getSignedUrl(@RequestParam("bucketName") String bucketName,
                               @RequestParam("fileName") String fileName) {
        return firebaseStorageService.generateSignedUrl(bucketName, fileName);
    }
}
