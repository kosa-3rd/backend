package com.example.demo.controller;

import com.example.demo.interfaces.controller.post.PostController;
import com.example.demo.interfaces.controller.post.PostDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
@AutoConfigureMockMvc
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("게시물 발행")
    void createPost() throws Exception {
        PostDto.CreatePostRequest createPostRequest = new PostDto.CreatePostRequest("title", "content");
        MvcResult authorization = mockMvc.perform(post("/api/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createPostRequest)))
                .andExpect(status().isCreated()).andReturn();
        String contentAsString = authorization.getResponse().getContentAsString();
        PostDto.CreatePostResponse createPostResponse = objectMapper.readValue(contentAsString, PostDto.CreatePostResponse.class);
        assertThat(createPostResponse.title()).isEqualTo(createPostRequest.title());
    }

    @Test
    @DisplayName("게시물 조회")
    void getPost() {

    }

    @Test
    @DisplayName("게시물 수정")
    void updatePost() {
    }

    @Test
    @DisplayName("게시물 삭제")
    void deletePost() {
    }


}