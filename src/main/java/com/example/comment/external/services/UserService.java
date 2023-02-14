package com.example.comment.external.services;

import com.example.comment.model.Comment;
import com.example.comment.model.StandardResponse;
import com.example.comment.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserService {

    @GetMapping("/user/{userId}")
    StandardResponse<User> getUser(@PathVariable("userId") Integer userId);
}
