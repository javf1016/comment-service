package com.example.comment.controller;

import com.example.comment.model.AddCommentRequest;
import com.example.comment.model.Comment;
import com.example.comment.model.StandardResponse;
import com.example.comment.repository.CommentRepository;
import com.example.comment.service.CommentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    private Logger logger = LoggerFactory.getLogger(CommentController.class);

    /**
     * Add a comment to a post
     */
    int retryCount=1;
    @PostMapping("/{postId}/comments")
    @Retry(name = "addCommentService", fallbackMethod = "addCommentFallback")
    public StandardResponse<Comment> addComment(@PathVariable("postId") Integer postId,
                                                @RequestBody AddCommentRequest addCommentRequest,
                                                HttpServletRequest request) {
        logger.info("Retry Count: {}"+retryCount);
        retryCount++;
        return commentService.addComment(addCommentRequest.getAuthorId(), postId, addCommentRequest.getBody(), request);
    }

    public StandardResponse<Comment> addCommentFallback(Exception ex){
        LocalDate localDate = LocalDate.now();
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Timestamp timestamp = Timestamp.from(instant);
        Comment comment = new Comment();
        comment.setAuthorId(123456);
        comment.setPostId(123456);
        comment.setBody("Service down");
        comment.setCreatedAt(timestamp);
        comment.setUpdatedAt(timestamp);
        return new StandardResponse<>(HttpStatus.SERVICE_UNAVAILABLE.value(), "Sevice down",
                null, null, comment, null);
    }

}

