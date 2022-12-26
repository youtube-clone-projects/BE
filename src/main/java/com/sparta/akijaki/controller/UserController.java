package com.sparta.akijaki.controller;

import com.sparta.akijaki.dto.CompleteResponseDto;
import com.sparta.akijaki.dto.LoginRequestDto;
import com.sparta.akijaki.dto.SignupRequestDto;
import com.sparta.akijaki.dto.WithdrawalRequestDto;
import com.sparta.akijaki.service.UserService;
import com.sparta.akijaki.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@Api(tags = {"User API"})
@RestController
//@CrossOrigin(originPatterns = "http://localhost:3000")
public class UserController {
    private final UserService userService;
    private final UserUtil userUtil;

    @PostMapping("/signup")
    @ApiOperation(value = "회원가입")
    public CompleteResponseDto signup(@Valid @RequestBody SignupRequestDto requestDto) {
            return userService.signup(requestDto);
    }


    @PostMapping("/login")
    @ApiOperation(value = "로그인")
    public CompleteResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        if(userUtil.checkUserStatus(loginRequestDto.getUsername())) {
            return userService.login(loginRequestDto, response);
        }else{
            return new CompleteResponseDto("탈퇴한 회원입니다", 400);
        }
    }
    //회원탈퇴
    @PutMapping("/withdrawal")
    public CompleteResponseDto withdrawal(@RequestBody WithdrawalRequestDto withdrawalRequestDto, HttpServletResponse response){
        return userService.withdrawal(withdrawalRequestDto,response);
    }
}
