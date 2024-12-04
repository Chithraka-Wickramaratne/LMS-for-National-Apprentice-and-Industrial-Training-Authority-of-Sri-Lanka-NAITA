package com.naita.student_lms.controller;

import com.naita.student_lms.entity.Hotel;
import com.naita.student_lms.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/courses")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/hotel")
    public String saveHotel(@RequestBody Hotel user) throws ExecutionException, InterruptedException {
        return hotelService.SaveHotel(user);
    }

    @GetMapping("/get-hotel")
    public List<Hotel> getHotelContent() throws ExecutionException, InterruptedException {
        return hotelService.getHotelContent();
    }

    @DeleteMapping("/delete-hotel/{id}")
    public String deleteHotel(@PathVariable String id) throws ExecutionException, InterruptedException {
        return hotelService.DeleteHotel(id);
    }

    @PutMapping("/edit-hotel/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody Hotel hotel) {
        try {
            hotel.setId(id);
            String updateTime = hotelService.updateHotel(hotel);
            return ResponseEntity.ok("Content updated at: " + updateTime);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating content: " + e.getMessage());
        }
    }

}
