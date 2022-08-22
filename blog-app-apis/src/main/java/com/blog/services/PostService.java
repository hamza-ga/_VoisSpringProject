package com.blog.services;

import com.blog.payloads.PostDto;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto, Integer userId);

    //update
    PostDto updatePost(PostDto postDto, Integer postId);

    //delete
    void deletePost(Integer postId);

    //get all posts
    List<PostDto> getAllPosts();

    //get single post
    PostDto getPostById(Integer postId);

    //get all posts by user
    List<PostDto> getPostsByUser(Integer userId);
}
