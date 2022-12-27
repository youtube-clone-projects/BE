package com.sparta.akijaki.repository;

import com.sparta.akijaki.entity.Comment;
import com.sparta.akijaki.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 외래키 검색 규칙
    // findBy + {fk를 관리하는 entity의 필드명, 첫 글자를 대문자} + _ + {fk entity의 식별자 필드명, 첫 글자를 대문자}
    List<Comment> findByPost_IdOrderByCreatedAtDesc(Long postId);


    @Query("SELECT DISTINCT c from Comment c join fetch c.user where c.post.id = :postId order by c.modifiedAt desc")
    List<Comment> findAllByPostIdWithUser(@Param("postId")Long postId);

    //마이페이지
    List<Comment> findAllByUser(User user);

    @Modifying // update delete쿼리만 사용
    @Query("delete from Comment c where c.id in :commentid")
    void deleteByCommentId(@Param("commentid") List<Long> id);

    @Query("select c.id FROM Comment c where c.post.id = :postid")
    List<Long> findIdsByPostId(@Param("postid") Long id);

}
