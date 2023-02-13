package com.example.comment.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

/**
 * TODO:
 * - Add the proper Spring annotations to the entity and its fields
 * - Include relations between entities
 */
@Entity
@Table(name = "comments")
@Data
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String body;
	@Column(name = "created_at")
	@DateTimeFormat
	private Timestamp createdAt;
	@Column(name = "updated_at")
	@DateTimeFormat
	private Timestamp updatedAt;
	@Column(name = "post_id")
	private Integer postId;
	@Column(name = "author_id")
	private Integer authorId;

}
