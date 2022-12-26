package com.sparta.akijaki.controller;

import com.sparta.akijaki.security.UserDetailsImpl;
import com.sparta.akijaki.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mypage")
public class MyPageController {
    private final MyPageService myPageService;
    @GetMapping("/myposts")
    public ResponseEntity<?> getMyPost(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return myPageService.getMyPost(userDetails.getUser());
    }

    @GetMapping("myposts/comments")
    public ResponseEntity<?> getMyComment(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return myPageService.getMyComment(userDetails.getUser());
    }
}
