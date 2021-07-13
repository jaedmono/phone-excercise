package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserRequest;
import com.example.demo.model.UserResponse;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    @PostMapping()
    public ResponseEntity<UserResponse> insertUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.insertUser(getUser(userRequest)));
    }

    @DeleteMapping()
    public ResponseEntity<UserResponse> deleteUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(getUser(userRequest)));
    }

    private User getUser(@RequestBody UserRequest userRequest) {
        return new ObjectMapper().convertValue(userRequest, User.class);
    }
}
