package com.naita.student_lms.controller;

import com.naita.student_lms.entity.Electrical;
import com.naita.student_lms.service.ElectricalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/courses")
public class ElectricalController {

    @Autowired
    private ElectricalService electricalService;

    @PostMapping("/electrical")
    public String SaveElectrical(@RequestBody Electrical user) throws ExecutionException, InterruptedException {
        return electricalService.SaveElectrical(user);
    }

    @GetMapping("/get-electrical")
    public List<Electrical> getElectricalContent() throws ExecutionException, InterruptedException {
        return electricalService.getElectricalContent();
    }

    @DeleteMapping("/delete-electrical/{id}")
    public String deleteHotel(@PathVariable String id) throws ExecutionException, InterruptedException {
        return electricalService.DeleteElectrical(id);
    }

    @PutMapping("/edit-electrical/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody Electrical electrical) {
        try {
            electrical.setId(id);
            String updateTime = electricalService.updateElectrical(electrical);
            return ResponseEntity.ok("Content updated at: " + updateTime);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating content: " + e.getMessage());
        }
    }
}
