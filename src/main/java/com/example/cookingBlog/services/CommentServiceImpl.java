package com.example.cookingBlog.services;

import com.example.cookingBlog.models.Comment;
import com.example.cookingBlog.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentsByRecipe(Long recipeId) {
        return commentRepository.findAllByRecipeIdOrderByDateOfCreateDesc(recipeId);
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteComments(Long blogId) {
        List<Long> commentsId = commentRepository.getAllCommentsIdFromOneBlog(blogId);
        for (Long l: commentsId){
            commentRepository.deleteCommentById(l);
        }
    }
}
