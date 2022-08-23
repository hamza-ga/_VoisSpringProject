package com.blog.controllers;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;
import com.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    //create
    @PostMapping("/create-post/user/{userId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId){
        PostDto createdPost = this.postService.createPost(postDto,userId);
        return new ResponseEntity<>(createdPost,HttpStatus.CREATED);
    }
}
