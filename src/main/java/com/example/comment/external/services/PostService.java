package com.example.comment.external.services;

import com.example.comment.model.Post;
import com.example.comment.model.StandardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "POST-SERVICE")
public interface PostService {

    @GetMapping("/posts/{postId}")
    StandardResponse<Post> getPost(@PathVariable("postId") Integer postId);
}
