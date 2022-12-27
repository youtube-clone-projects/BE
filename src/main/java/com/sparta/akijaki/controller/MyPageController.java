package com.sparta.akijaki.controller;

import com.sparta.akijaki.security.UserDetailsImpl;
import com.sparta.akijaki.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mypage")
public class MyPageController {
    private final MyPageService myPageService;
    @GetMapping("/myposts")
    public ResponseEntity<?> getMyPost(HttpServletRequest httpServletRequest){
        return myPageService.getMyPost(httpServletRequest);
    }

//    @GetMapping("/myposts")
//    public ResponseEntity<?> getMyPost2(@AuthenticationPrincipal UserDetailsImpl userDetails){
//        return myPageService.getMyPost2(userDetails.getUser());
//    }

    @GetMapping("myposts/comments")
    public ResponseEntity<?> getMyComment(HttpServletRequest httpServletRequest){
        return myPageService.getMyComment(httpServletRequest);
    }
}
