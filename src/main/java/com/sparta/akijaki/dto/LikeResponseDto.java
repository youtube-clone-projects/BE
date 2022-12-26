package com.sparta.akijaki.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LikeResponseDto {
    private String msg;
    private int statusCode;

    private boolean likeCheck;

    public LikeResponseDto(String msg) {
        this.msg = msg;
        this.statusCode = HttpStatus.OK.value();
    }

    public LikeResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
    public LikeResponseDto(String msg, boolean likeCheck) {
        this.msg = msg;
        this.statusCode = HttpStatus.OK.value();
        this.likeCheck = likeCheck;
    }
}
