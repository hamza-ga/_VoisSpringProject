package com.blog.repositories;

import com.blog.entities.Post;
import com.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
}
