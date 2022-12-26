package com.sparta.akijaki.repository;

import com.sparta.akijaki.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
    List<Post> findByCategory(String category);

    //게시글 검색
    List<Post> findAllByTitleContainingOrderByCreatedAtDesc(String search);

    //마이페이지
    List<Post> findAllByUser(User user);

}

