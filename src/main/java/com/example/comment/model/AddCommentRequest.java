package com.example.comment.model;

import lombok.Data;

@Data
public class AddCommentRequest {
    private Integer authorId;
    private String body;

}