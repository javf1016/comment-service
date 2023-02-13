package com.example.comment.controller;

import com.example.comment.model.AddCommentRequest;
import com.example.comment.model.Comment;
import com.example.comment.model.StandardResponse;
import com.example.comment.repository.CommentRepository;
import com.example.comment.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * Add a comment to a post
     */
    @PostMapping("/{postId}/comments")
    public StandardResponse<Comment> addComment(@PathVariable("postId") Integer postId,
                                                @RequestBody AddCommentRequest addCommentRequest,
                                                HttpServletRequest request) {
        return commentService.addComment(addCommentRequest.getAuthorId(), postId, addCommentRequest.getBody(), request);
    }

}

