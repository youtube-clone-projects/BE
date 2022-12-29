package com.sparta.akijaki.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CompleteResponseDto {

    private String msg;
    private int statusCode;

    public CompleteResponseDto(String msg) {
        this.msg = msg;
        this.statusCode = HttpStatus.OK.value();

    }

    public CompleteResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
