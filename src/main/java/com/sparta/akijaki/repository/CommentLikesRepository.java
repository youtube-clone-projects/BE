package com.sparta.akijaki.repository;


import com.sparta.akijaki.entity.Comment;
import com.sparta.akijaki.entity.CommentLikes;
import com.sparta.akijaki.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {

    Long countByCommentAndLikeCheckIsTrue(Comment comment);
    Optional<CommentLikes> findByUserAndCommentAndLikeCheckIsTrue(User user, Comment comment);
    Optional<CommentLikes> findByUserAndComment(User user, Comment comment);

    @Modifying
    @Query("delete from CommentLikes cl where cl.comment.id in :commentid")
    void deleteByCommentId(@Param("commentid") List<Long> id);

}