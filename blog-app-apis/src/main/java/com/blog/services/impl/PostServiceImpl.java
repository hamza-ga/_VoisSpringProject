package com.blog.services.impl;

import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exeptions.ResourceNotFoundExeption;
import com.blog.payloads.PostDto;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundExeption("User","User id", userId));
        Post post = this.modelMapper.map(postDto, Post.class);
        post.setAddedDate(new Date());
        post.setUser(user);
        Post newPost = this.postRepository.save(post);
        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundExeption("Post","post id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        Post updatedPost = this.postRepository.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundExeption("Post","post id", postId));
        this.postRepository.delete(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> allPosts = this.postRepository.findAll();
        return allPosts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundExeption("Post","post id",postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundExeption("User","User id", userId));
        List<Post> posts = this.postRepository.findByUser(user);
        return posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }
}
