package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.UserPhonesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/users/{idUser}/phones")
public class PhoneController {

    @Autowired
    private UserPhonesService userPhonesService;

    @GetMapping()
    public ResponseEntity<List<UserPhonesResponse>> getUserPhones(@PathVariable("idUser") @NotNull String idUser){
        return ResponseEntity.status(HttpStatus.OK).body(userPhonesService.getUserPhones(idUser));
    }

    @PostMapping()
    public ResponseEntity<UserPhonesResponse> insertUserPhone(@PathVariable("idUser") @NotNull String idUser,
                                                        @RequestBody PhoneRequest phoneRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userPhonesService.insertUserPhone(getUserPhones(phoneRequest)));
    }

    @PutMapping()
    public ResponseEntity<UserPhonesResponse> updateUserPhone(@PathVariable("idUser") @NotNull String idUser,
                                                        @RequestBody PhoneRequest phoneRequest){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userPhonesService.updateUserPhone(getUserPhones(phoneRequest)));
    }

    @DeleteMapping()
    public ResponseEntity<UserPhonesResponse> deleteUserPhone(@PathVariable("idUser") @NotNull String idUser,
                                                   @RequestBody PhoneRequest phoneRequest){

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userPhonesService.deleteUserPhone(getUserPhones(phoneRequest)));
    }

    private UserPhones getUserPhones(PhoneRequest phoneRequest) {

        return new ObjectMapper().convertValue(phoneRequest, UserPhones.class);
    }

}
