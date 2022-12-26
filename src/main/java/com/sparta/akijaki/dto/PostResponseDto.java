package com.sparta.akijaki.dto;

import com.sparta.akijaki.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostResponseDto {
    private Long num;
    private String username;

    private String content;
    private String title;

    private String videoFile;

    private String imageFile;

    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;
    private boolean postLikeCheck;

    private Long likeCount=0l;

    private List<CommentResponseDto> commentList = new ArrayList<>();

    public PostResponseDto(Post post) {
        this.num = post.getId();
        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.videoFile = post.getVideoFile();
        this.imageFile = post.getImageFile();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

    public PostResponseDto(Post post, CommentListResponseDto commentList, Long postLikeCnt) {
        this.num = post.getId();
        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.videoFile = post.getVideoFile();
        this.imageFile = post.getImageFile();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.likeCount = postLikeCnt;
        this.commentList = commentList.getCommentList();
    }


    public PostResponseDto(Post post, boolean postLikeCheck,CommentListResponseDto commentList, Long postLikeCnt) {
        this.num = post.getId();
        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.videoFile = post.getVideoFile();
        this.imageFile = post.getImageFile();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.postLikeCheck = postLikeCheck;
        this.likeCount = postLikeCnt;
        this.commentList = commentList.getCommentList();
    }

    public PostResponseDto(Post post, Long postLikeCnt) {
        this.num = post.getId();
        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.videoFile = post.getVideoFile();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.imageFile = post.getImageFile();
        this.likeCount = postLikeCnt;
    }
}
