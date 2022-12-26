package com.sparta.akijaki.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class SignupRequestDto {

    @NotBlank(message = "아이디를 입력해주세요")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    private String nickname;
    private boolean admin = false;
    private String adminToken = "";
}
