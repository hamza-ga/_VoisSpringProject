package com.blog.controllers;

import com.blog.entities.Post;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDto;
import com.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    //create
    @PostMapping("/create/user/{userId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId){
        PostDto createdPost = this.postService.createPost(postDto,userId);
        return new ResponseEntity<>(createdPost,HttpStatus.CREATED);
    }

    //get posts by user
    @GetMapping("/user-s-posts/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") Integer userId){
        List<PostDto> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    //get all posts
    @GetMapping("/")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        List<PostDto> allPosts = this.postService.getAllPosts();
        return new ResponseEntity<>(allPosts,HttpStatus.OK);
    }

    //get post by Id
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId){
        PostDto postDto = this.postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    //delete post
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{postId}")
    public ApiResponse deletePost(@PathVariable("postId") Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("post is successfully deleted", true);
    }

    //Edit post
    @PutMapping("/update/{postId}")
    public ResponseEntity<PostDto> updatePost( @RequestBody PostDto postDto, @PathVariable("postId") Integer postId){
        PostDto updatedPost = this.postService.updatePost(postDto,postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }


}
