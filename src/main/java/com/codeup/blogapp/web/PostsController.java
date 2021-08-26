package com.codeup.blogapp.web;

import com.codeup.blogapp.data.category.Category;
import com.codeup.blogapp.data.post.Post;
import com.codeup.blogapp.data.post.PostsRepository;
import com.codeup.blogapp.data.services.EmailService;
import com.codeup.blogapp.data.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {


    private final PostsRepository postsRepository;
    private final EmailService emailService;


     public PostsController(PostsRepository postsRepository, EmailService emailService) {
                this.postsRepository = postsRepository;
                this.emailService = emailService;
    }

    // depending on HTTP METHOD GET POST PUT DELETE on endpoint /api/posts -> the method with that annotation fires
    @GetMapping()
    private List<Post> getPosts() {

        return postsRepository.findAll();
    }

    @GetMapping("{id}")
    private Post getPostById(@PathVariable Long id) {
         return postsRepository.findById(id).get();
    }


    @PostMapping
    private void createPosts(@RequestBody Post newPost) {
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
