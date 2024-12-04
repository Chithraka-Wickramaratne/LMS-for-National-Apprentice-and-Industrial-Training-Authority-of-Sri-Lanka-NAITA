package com.naita.student_lms.controller;

import com.naita.student_lms.entity.Software;
import com.naita.student_lms.service.SoftwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/courses")

public class SoftwareController {

    @Autowired
    private SoftwareService softwareService;

    @PostMapping("/software")
    public String saveSoftware(@RequestBody Software user) throws ExecutionException, InterruptedException {
        return softwareService.SaveSoftware(user);
    }

    @GetMapping("/get-software")
    public List<Software> getSoftwareContent() throws ExecutionException, InterruptedException {
        return softwareService.getSoftwareContent();
    }

    @DeleteMapping("/delete-software/{id}")
    public String deleteSoftware(@PathVariable String id) throws ExecutionException, InterruptedException {
        return softwareService.DeleteSoftware(id);
    }

    @PutMapping("/edit-software/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody Software software) {
        try {
            software.setId(id);
            String updateTime = softwareService.updateSoftware(software);
            return ResponseEntity.ok("Content updated at: " + updateTime);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating content: " + e.getMessage());
        }
    }
}
