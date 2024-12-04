package com.naita.student_lms.controller;

import com.naita.student_lms.entity.Automobile;
import com.naita.student_lms.service.AutomobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/courses")
public class AutomobileController {

    @Autowired
    private AutomobileService automobileService;

    @PostMapping("/automobile")
    public String SaveAutomobile(@RequestBody Automobile user) throws ExecutionException, InterruptedException {
        return automobileService.SaveAutomobile(user);
    }

    @GetMapping("/get-automobile")
    public List<Automobile> getAutomobileContent() throws ExecutionException, InterruptedException {
        return automobileService.getAutomobileContent();
    }

    @DeleteMapping("/delete-automobile/{id}")
    public String deleteAutomobile(@PathVariable String id) throws ExecutionException, InterruptedException {
        return automobileService.DeleteAutomobile(id);
    }

    @PutMapping("/edit-automobile/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody Automobile automobile) {
        try {
            automobile.setId(id);
            String updateTime = automobileService.updateAutomobile(automobile);
            return ResponseEntity.ok("Content updated at: " + updateTime);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating content: " + e.getMessage());
        }
    }
}
