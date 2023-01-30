package com.example.cookingBlog.repositories;

import com.example.cookingBlog.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByRecipeIdOrderByDateOfCreateDesc(Long recId);

    @Query(nativeQuery = true, value = "select comment_id from comment_table where blog_id = ?1")
    @Modifying
    List<Long> getAllCommentsIdFromOneBlog(Long blogId);

    void deleteCommentById(Long commentId);
}
