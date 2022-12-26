package com.sparta.akijaki.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
@Getter
public class WithdrawalRequestDto {
    private String username;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
