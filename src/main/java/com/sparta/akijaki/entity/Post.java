package com.sparta.akijaki.entity;

import com.sparta.akijaki.dto.PostRequestDto;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 제목
    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(nullable = false)
    private String category;

    @Column
    private String videoFile;
    @Column
    private String imageFile;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE) // , cascade = CascadeType.REMOVE
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE) // , cascade = CascadeType.REMOVE
    private List<PostLikes> postLikes = new ArrayList<>();

    public Post(String title, String content, String category, String videoFile, String imageFile,  User user) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.videoFile = videoFile;
        this.imageFile = imageFile;
        this.user = user;
    }

    public void update(String title, String content,  String category,String videoFile, String imageFile) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.videoFile = videoFile;
        this.imageFile = imageFile;
    }
}