package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PhoneController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository repository;

    private User user;
    private List<User> userList;

    private final static String STRING_JSON_USER = "{"+
            "\"userId\":\"93f3ed0a-92bd-4c82-ba0e-c098111cef59\","+
            "\"userName\":\"john_user\","+
            "\"password\":\"*****\","+
            "\"emailAddress\":\"john@example.com\","+
            "\"preferredPhoneNumber\":\"+353881234567\" }";

    @BeforeEach
    public void init(){
        user = new User();
        user.setId(1L);
        user.setUserId("123545");
        user.setUserName("");
        user.setEmailAddress("");
        user.setPreferredPhoneNumber("+3534852625");

        userList = Stream.of(user)
                .collect(Collectors.toList());
    }

    @Test
    public void getAllUsersResponseOK() throws Exception {
        when(repository.findAll()).thenReturn(userList);
        this.mockMvc.perform(get("/users/{idUser}/phones")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("{'message':'ok'}"))
                .andExpect(status().isOk());
    }

    @Test
    public void insertUserResponseCreated() throws Exception {
        when(repository.save(any(User.class))).thenReturn(user);
        this.mockMvc.perform(post("/users/{idUser}/phones")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(STRING_JSON_USER))
                .andExpect(content().json("{'message':'ok'}"))
                .andExpect(status().isCreated());
    }


    @Test
    public void deleteUserResponseOk() throws Exception {
        this.mockMvc.perform(delete("/users/{idUser}/phones")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(STRING_JSON_USER))
                .andExpect(content().json("{'message':'ok'}"))
                .andExpect(status().isAccepted());
    }
}
