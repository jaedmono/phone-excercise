package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserResponse;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    private final ObjectMapper mapper = new ObjectMapper();

    public List<UserResponse> getUsers() {
        return mapper.convertValue(repository.findAll(), new TypeReference<List<UserResponse>>(){});
    }

    public UserResponse insertUser(User user) {
        return mapper.convertValue(repository.save(user), UserResponse.class) ;
    }

    public UserResponse deleteUser(User user) {
        repository.delete(user);
        return mapper.convertValue(user, UserResponse.class) ;
    }
}
