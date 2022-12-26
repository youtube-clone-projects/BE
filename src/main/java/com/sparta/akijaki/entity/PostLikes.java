package com.sparta.akijaki.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false)
    private boolean likeCheck;

    public PostLikes(User user, Post post) {
        this.user = user;
        this.post = post;
        this.likeCheck = true;
    }

    public void likeCancle() {
        this.likeCheck=false;
    }

    public void likepost() {
        this.likeCheck = true;
    }
}
