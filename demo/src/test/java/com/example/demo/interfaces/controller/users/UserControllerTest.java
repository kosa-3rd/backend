package com.example.demo.interfaces.controller.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void signup() throws Exception {
        UserDto.SignUpRequest request = new UserDto.SignUpRequest("test", "test");
        MvcResult authorization = mockMvc.perform(post("/api/users/signup")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))).andReturn();
        assertEquals(200, authorization.getResponse().getStatus());
        String contentAsString = authorization.getResponse().getContentAsString();
        UserDto.SignUpResponse response = objectMapper.readValue(contentAsString, UserDto.SignUpResponse.class);
        assertEquals("test", response.email());
        assertNotNull(response.token());
    }

    @Test
    void login() throws Exception {
        UserDto.LoginRequest request = new UserDto.LoginRequest("test", "test");
        MvcResult authorization = mockMvc.perform(post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))).andReturn();
        assertEquals(200, authorization.getResponse().getStatus());
        String contentAsString = authorization.getResponse().getContentAsString();
        UserDto.LoginResponse response = objectMapper.readValue(contentAsString, UserDto.LoginResponse.class);
        assertEquals("test", response.email());
        assertNotNull(response.token());
    }


}