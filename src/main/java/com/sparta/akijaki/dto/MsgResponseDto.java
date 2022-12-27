package com.sparta.akijaki.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class MsgResponseDto {
    private String msg;

    private int statusCode;



    public MsgResponseDto(String msg){
        this.msg = msg;
        this.statusCode = HttpStatus.OK.value();
    }
}
