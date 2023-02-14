package com.example.comment.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Post {

    private Integer id;

    private Integer views;

    private String title;

    private String body;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Integer userId;

}
