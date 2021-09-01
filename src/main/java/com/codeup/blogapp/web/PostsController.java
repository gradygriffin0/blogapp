package com.codeup.blogapp.web;

import com.codeup.blogapp.data.category.Category;
import com.codeup.blogapp.data.post.Post;
import com.codeup.blogapp.data.post.PostsRepository;
import com.codeup.blogapp.data.services.EmailService;
import com.codeup.blogapp.data.user.User;
import com.codeup.blogapp.data.user.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {


    private final PostsRepository postsRepository;
    private final EmailService emailService;
    private final UserRepository userRepository;


     public PostsController(PostsRepository postsRepository, EmailService emailService, UserRepository userRepository) {
                this.postsRepository = postsRepository;
                this.emailService = emailService;
                this.userRepository = userRepository;
    }

    // depending on HTTP METHOD GET POST PUT DELETE on endpoint /api/posts -> the method with that annotation fires
    @GetMapping()
    @PreAuthorize("!hasAuthority('USER')")
    private List<Post> getPosts() {

        return postsRepository.findAll();
    }

    @GetMapping("{id}")
    private Post getPostById(@PathVariable Long id) {
         return postsRepository.findById(id).get();
    }


    @PostMapping
    private void createPosts(@RequestBody Post newPost, OAuth2Authentication auth) {
         String email = auth.getName();
         User user = userRepository.findByEmail(email).get();
         newPost.setUser(user);
        postsRepository.save(newPost);
        emailService.prepareAndSend(newPost, "Test Subject", "Test Body");
    }


    @PutMapping()
    private void updatePost( @RequestBody Post post) {
        postsRepository.save(post);
    }

    @DeleteMapping("/{id}")
    private void deletePost(@PathVariable Long id) {
        postsRepository.deleteById(id);
    }

}
