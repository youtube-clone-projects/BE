package com.sparta.akijaki.service;

import com.sparta.akijaki.dto.*;
import com.sparta.akijaki.dto.SignupRequestDto;
import com.sparta.akijaki.entity.User;
import com.sparta.akijaki.entity.UserRoleEnum;
import com.sparta.akijaki.jwt.JwtUtil;
import com.sparta.akijaki.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Value("${admin.secret.token}")
    private String admin_token;

    //회원가입
    @Transactional
    public CompleteResponseDto signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        System.out.println("username = " + username);
        boolean isExistUsername = userRepository.existsByUsername(username);
        System.out.println("isExistUsername = " + isExistUsername);
        if (isExistUsername) {
            throw new IllegalArgumentException("중복된 username 입니다.");
        }

        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()){
            if (!requestDto.getAdminToken().equals(admin_token)) {
                throw new IllegalArgumentException("관리자 토큰값이 일치하지 않습니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, role);

        userRepository.save(user);

        return new CompleteResponseDto("회원가입 성공");
    }

    //로그인
    @Transactional(readOnly = true)
    public CompleteResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse) {
        //사용자 확인
        User user = userRepository.findByUsername(loginRequestDto.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("회원을 찾을 수 없습니다.")
        );

        //비밀번호 확인
        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        //1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword());

        //2. 실제로 검증(사용자 비밀번호 체크)이 이루어지는 부분
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //3. 토큰을 생성하여 Header 에 저장
        httpServletResponse.addHeader(
                JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(authentication));

        return new CompleteResponseDto("님 환영합니다!");
    }

    //회원탈퇴
    @Transactional
    public CompleteResponseDto withdrawal(WithdrawalRequestDto withdrawalRequestDto, HttpServletResponse response) {
        //사용자 확인
        User user = userRepository.findByUsername(withdrawalRequestDto.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("회원을 찾을 수 없습니다.")
        );
        //비밀번호 확인
        if(!passwordEncoder.matches(withdrawalRequestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        user.setUserStatus(!user.isUserStatus());
        return new CompleteResponseDto("회원탈퇴를 완료했습니다");
    }
}
