package com.sparta.akijaki.service;

import com.sparta.akijaki.dto.CommentResponseDto;
import com.sparta.akijaki.dto.MsgResponseDto;
import com.sparta.akijaki.dto.PostResponseDto;
import com.sparta.akijaki.entity.Comment;
import com.sparta.akijaki.entity.Post;
import com.sparta.akijaki.repository.CommentRepository;
import com.sparta.akijaki.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    //마이페이지 내가 작성한 게시글? 동영상 가져오기
    public ResponseEntity<?> getMyPost(User user) {
        List<Post> posts = postRepository.findAllByUser(user);
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();


        if (posts.isEmpty()) {
            return new ResponseEntity<>(new MsgResponseDto("작성한 게시글이 없습니다"), HttpStatus.OK);

        }
        for (Post post : posts) {
            PostResponseDto postResponseDto = new PostResponseDto(post);
            postResponseDtoList.add(postResponseDto);

        }
        return new ResponseEntity<>(postResponseDtoList, HttpStatus.OK);
    }

    //내가 작성한 댓글 가져오기
    public ResponseEntity<?> getMyComment(User user) {
        List<Comment> commentList = commentRepository.findAllByUser(user);

        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        if(commentList.isEmpty()){
            return new ResponseEntity<>(new MsgResponseDto("작성한 댓글이 없습니다"),HttpStatus.OK);
        }
        for(Comment comment : commentList){
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            commentResponseDtoList.add(commentResponseDto);
        }
        return new ResponseEntity<>(commentResponseDtoList,HttpStatus.OK);
    }
}
