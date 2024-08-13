package com.example.demo.interfaces.controller.post.dto;


import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class PostInfoDTO {

    private final long id;
    private final String content;
    private final String station;
    private final LocalDateTime createdAt;
    private final String nickname;

    public PostInfoDTO(long id, String content, String station, LocalDateTime createdAt, String nickname) {
        this.id = id;
        this.content = content;
        this.station = station;
        this.createdAt = createdAt;
        this.nickname = nickname;
    }
}
