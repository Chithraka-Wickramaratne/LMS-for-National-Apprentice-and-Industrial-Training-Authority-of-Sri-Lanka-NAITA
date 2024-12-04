package com.naita.student_lms.controller;

import com.naita.student_lms.entity.User;
import com.naita.student_lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public String saveUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.SaveUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) throws ExecutionException, InterruptedException {
        User user = userService.authenticateUser(email, password);

        if (user != null) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(401).body("Invalid email or password");
    }

    @GetMapping("/get-users")
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable String id) throws ExecutionException, InterruptedException {
        return userService.deleteUser(id);
    }

    @PutMapping("/edit-user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody User user) {
        try {
            user.setId(id);
            String updateTime = userService.updateUser(user);
            return ResponseEntity.ok("User updated at: " + updateTime);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating user: " + e.getMessage());
        }
    }

}
