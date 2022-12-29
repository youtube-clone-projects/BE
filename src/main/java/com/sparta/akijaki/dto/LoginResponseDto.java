package com.sparta.akijaki.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private String msg;

    private int statusCode;
    private String username;

    public LoginResponseDto(String msg, int statusCode, String username) {
        this.msg = msg;
        this.statusCode = statusCode;
        this.username = username;
    }

    public LoginResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
