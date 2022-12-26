package com.sparta.akijaki.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @Column(nullable = false)
    private boolean likeCheck;

    public CommentLikes(User user, Comment comment) {
        this.user=user;
        this.comment=comment;
        this.likeCheck=true;
    }

    public void likeCancle() { this.likeCheck=false; }
    public void likeComment() { this.likeCheck=true; }
}
