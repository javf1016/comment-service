package com.example.comment.service;

import com.example.comment.model.Comment;
import com.example.comment.model.StandardResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {

    /**
     * Add a comment to a post
     */
    StandardResponse<Comment> addComment(Integer authorId, Integer postId, String body, HttpServletRequest request);

}
