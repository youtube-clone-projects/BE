package com.sparta.akijaki.service;

import com.sparta.akijaki.dto.CommentRequestDto;
import com.sparta.akijaki.dto.CommentResponseDto;
import com.sparta.akijaki.dto.CompleteResponseDto;
import com.sparta.akijaki.entity.Comment;
import com.sparta.akijaki.entity.Post;
import com.sparta.akijaki.entity.User;
import com.sparta.akijaki.entity.UserRoleEnum;
import com.sparta.akijaki.jwt.JwtUtil;
import com.sparta.akijaki.repository.CommentLikesRepository;
import com.sparta.akijaki.repository.CommentRepository;
import com.sparta.akijaki.repository.PostRepository;
import com.sparta.akijaki.repository.UserRepository;
import com.sparta.akijaki.util.UserUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final JwtUtil jwtUtil;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final UserUtil userUtil;
    private final CommentLikesRepository commentLikesRepository;

    @Transactional
    //댓글 저장하기
    public CommentResponseDto saveComment(Long postId, CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest) {
        //로그인 여부 확인
        User user = userUtil.getUserInfo(httpServletRequest);
        //게시글 저장 여부 확인
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다")
        );
        //댓글 저장
        Comment comment = new Comment(commentRequestDto,  post, user);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }
    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest) {
        //로그인 여부 확인
        User user = userUtil.getUserInfo(httpServletRequest);
        //댓글 존재 여부 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException(("댓글이 존재하지 않습니다")
                ));
        //ADMIN 권한, username 확인 후 댓글 업데이트
        if(comment.getUser().getUsername().equals(user.getUsername()) || user.getRole() == UserRoleEnum.ADMIN){
            comment.update(commentRequestDto);
        }else{
            throw new IllegalArgumentException("올바른 사용자가 아닙니다");
        }
        Long commentCnt = commentLikesRepository.countByCommentAndLikeCheckIsTrue(comment);
        //수정된 댓글 반환
        return new CommentResponseDto(comment, commentCnt);
    }
    @Transactional
    //댓글 삭제하기
    public CompleteResponseDto deleteComment(Long commentId, HttpServletRequest httpServletRequest) {
        //로그인 여부 확인
        User user = userUtil.getUserInfo(httpServletRequest);
        //댓글 저장 여부 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException(("댓글이 존재하지 않습니다")
                ));
        //ADMIN 권한, username 확인 후 댓글 삭제
        if(comment.getUser().getUsername().equals(user.getUsername()) || user.getRole() == UserRoleEnum.ADMIN){
            commentRepository.delete(comment);
        }else{
            throw new IllegalArgumentException("올바른 사용자가 아닙니다");
        }
        //삭제 완료 반환
        return new CompleteResponseDto("삭제 완료");
    }
}
