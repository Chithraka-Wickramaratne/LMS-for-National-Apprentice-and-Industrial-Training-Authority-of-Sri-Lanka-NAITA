package com.naita.student_lms.controller;

import com.naita.student_lms.entity.Ict;
import com.naita.student_lms.service.IctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/courses")
public class IctConroller {

    @Autowired
    private IctService ictService;

    @PostMapping("/ict")
    public String saveIct(@RequestBody Ict user) throws ExecutionException, InterruptedException {
        return ictService.SaveIct(user);
    }

    @GetMapping("/get-ict")
    public List<Ict> getIctContent() throws ExecutionException, InterruptedException {
        return ictService.getIctContent();
    }

    @DeleteMapping("/delete-ict/{id}")
    public String deleteIct(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ictService.DeleteIct(id);
    }

    @PutMapping("/edit-ict/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody Ict ict) {
        try {
            ict.setId(id);
            String updateTime = ictService.updateIct(ict);
            return ResponseEntity.ok("Content updated at: " + updateTime);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating content: " + e.getMessage());
        }
    }

}
