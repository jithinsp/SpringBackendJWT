package com.corner.app.controller;

import com.corner.app.dto.HelloResponse;
import com.corner.app.entity.Users;
import com.corner.app.service.jwt.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserServiceImpl userService;
    @GetMapping("/hi")
    public HelloResponse adminMsg(){
        return new HelloResponse("Welcome back admin");
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        System.out.println("deleting"+userId);
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }


}