package com.sparta.akijaki.dto;


import com.sparta.akijaki.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostReadResponseDto {
    private String title;

    private String content;

    private String username;

    public PostReadResponseDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUser().getUsername();
    }
}

