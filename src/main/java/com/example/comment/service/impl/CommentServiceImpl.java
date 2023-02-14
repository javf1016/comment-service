package com.example.comment.service.impl;

import com.example.comment.external.services.UserService;
import com.example.comment.model.Comment;
import com.example.comment.model.StandardResponse;
import com.example.comment.model.User;
import com.example.comment.repository.CommentRepository;
import com.example.comment.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Override
    public StandardResponse<Comment> addComment(Integer authorId, Integer postId, String body, HttpServletRequest request) {
        StandardResponse<User> user = userService.getUser(authorId);
        if(user.getData()==null){
            return new StandardResponse<>(HttpStatus.NOT_FOUND.value(), "Get post by user dont success "+ user.getMessage(),
                    request.getRequestURI(), null, null, null);
        }
        LocalDate localDate = LocalDate.now();
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Timestamp timestamp = Timestamp.from(instant);
        Comment comment = new Comment();
        comment.setAuthorId(authorId);
        comment.setPostId(postId);
        comment.setBody(body);
        comment.setCreatedAt(timestamp);
        comment.setUpdatedAt(timestamp);
        Comment savedComment = commentRepository.save(comment);
        return new StandardResponse<>(HttpStatus.OK.value(), "Creación de comentario exitosa",
                request.getRequestURI(), null, savedComment, null);
    }

}
