package com.codeup.blogapp.web;

import com.codeup.blogapp.data.category.Category;
import com.codeup.blogapp.data.post.Post;
import com.codeup.blogapp.data.post.PostsRepository;
import com.codeup.blogapp.data.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {


    private final PostsRepository postsRepository;

     public PostsController(PostsRepository postsRepository) {
                this.postsRepository = postsRepository;
    }

    // depending on HTTP METHOD GET POST PUT DELETE on endpoint /api/posts -> the method with that annotation fires
    @GetMapping()
    private List<Post> getPosts() {

        return postsRepository.findAll();
    }

    @GetMapping("{id}")
    private Post getPostById(@PathVariable Long id) {
         return postsRepository.getById(id);
    }


    @PostMapping
    private void createPosts(@RequestBody Post newPost) {
        postsRepository.save(newPost);
    }


    @PutMapping("/{id}")
    private void updatePost(@PathVariable Long id, @RequestBody Post post) {
        postsRepository.save(post);
    }

    @DeleteMapping("/{id}")
    private void deletePost(@PathVariable Long id) {
        postsRepository.deleteById(id);
    }

}
