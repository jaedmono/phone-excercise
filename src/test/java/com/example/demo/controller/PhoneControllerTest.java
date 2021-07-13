package com.example.demo.controller;

import com.example.demo.PhoneModel;
import com.example.demo.model.UserPhones;
import com.example.demo.repository.UserPhonesRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PhoneController.class)
public class PhoneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserPhonesRepository repository;

    private UserPhones userPhones;
    private List<UserPhones> userPhonesList;

    private final static String STRING_JSON_USER_PHONE = "{"+
            "\"phoneId\":\"2a810635-18d0-40ce-ada6-0c0fd1181225\","+
            "\"phoneName\":\"John's Pixel\","+
            "\"phoneModel\":\"ANDROID\","+
            "\"phoneNumber\":\"+353881234567\" }";

    @BeforeEach
    public void init(){
        userPhones = new UserPhones();
        userPhones.setId(1L);
        userPhones.setUserId("123545");
        userPhones.setPhoneModel(PhoneModel.ANDROID.name());
        userPhones.setPhoneNumber("+3534852625");
        userPhones.setPhoneName("John's Pixel");

        userPhonesList = Stream.of(userPhones)
                .collect(Collectors.toList());
    }

    @Test
    public void getAllUserPhonesResponseOK() throws Exception {
        when(repository.findAll()).thenReturn(userPhonesList);
        this.mockMvc.perform(get("/users/123456/phones")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("{'message':'ok'}"))
                .andExpect(status().isOk());
    }

    @Test
    public void insertUserPhoneResponseCreated() throws Exception {
        when(repository.save(any(UserPhones.class))).thenReturn(userPhones);
        this.mockMvc.perform(post("/users/123456/phones")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(STRING_JSON_USER_PHONE))
                .andExpect(content().json("{'message':'ok'}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateUserPhoneResponseACCEPTED() throws Exception {
        when(repository.save(any(UserPhones.class))).thenReturn(userPhones);
        this.mockMvc.perform(put("/users/123456/phones")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(STRING_JSON_USER_PHONE))
                .andExpect(content().json("{'message':'ok'}"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void deleteUserPhoneResponseOk() throws Exception {
        this.mockMvc.perform(delete("/users/123456/phones")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(STRING_JSON_USER_PHONE))
                .andExpect(content().json("{'message':'ok'}"))
                .andExpect(status().isAccepted());
    }
}
