package com.sparta.akijaki.controller;

import com.sparta.akijaki.dto.CommentRequestDto;
import com.sparta.akijaki.dto.CommentResponseDto;
import com.sparta.akijaki.dto.CompleteResponseDto;
import com.sparta.akijaki.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/posts/{postId}")
@Api(tags = {"Comment API"})
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글 생성
    @PostMapping("/comments")
    @ApiOperation(value = "댓글 작성")
    public CommentResponseDto saveComment(@PathVariable Long postId, @Valid @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest){

        return commentService.saveComment(postId, commentRequestDto, httpServletRequest);
    }
    //댓글 수정
    @PutMapping("/comments/{commentId}")
    @ApiOperation(value = "댓글 수정")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @Valid @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest){
        return commentService.updateComment(commentId, commentRequestDto, httpServletRequest);
    }
    //댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    @ApiOperation(value = "댓글 삭제")
    public CompleteResponseDto deleteComment(@PathVariable Long commentId, HttpServletRequest httpServletRequest) {
        return commentService.deleteComment(commentId, httpServletRequest);
    }
}
