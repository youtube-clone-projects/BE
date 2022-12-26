package com.sparta.akijaki.util;

import com.sparta.akijaki.dto.CompleteResponseDto;
import com.sparta.akijaki.entity.User;
import com.sparta.akijaki.jwt.JwtUtil;
import com.sparta.akijaki.repository.UserRepository;
import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public User getUserInfo(HttpServletRequest httpServletRequest) {
        String token = jwtUtil.resolveToken(httpServletRequest);
        Claims claims;

        //유효한 토큰일 경우 수정 가능
        if (token != null && jwtUtil.validateToken(token)) {
            // 토큰에서 사용자 정보 가져오기
            claims = jwtUtil.getUserInfoFromToken(token);

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            return userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        } else {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }
    }


    public boolean checkUserStatus(String username){
        User user = userRepository.findByUsername(username).orElseThrow(
                ()-> new IllegalArgumentException("회원가입이 필요합니다")
        );
        if(!user.isUserStatus()){
            return user.isUserStatus();
        }
        return true;
    }
}

