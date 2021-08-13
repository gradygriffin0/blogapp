package com.codeup.blogapp.web;

import com.codeup.blogapp.data.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {


    // depending on HTTP METHOD GET POST PUT DELETE on endpoint /api/posts -> the method with that annotation fires
    @GetMapping()
    private List<Post> getPosts(){
        return new ArrayList<Post>(){{
            add(new Post(1, "My first post", "1asdfasdfasdfasdfasdfasdfasdfasdfasdf"));
            add(new Post(2, "My second post", "2asdfasdfasdfasdfasdfasdfasdfasdfasdf"));
            add(new Post(3, "My third post", "3asdfasdfasdfasdfasdfasdfasdfasdfasdf"));
            add(new Post(4, "My fourth post", "4asdfasdfasdfasdfasdfasdfasdfasdfasdf"));

        }};
    }

    @GetMapping("{id}")
    private Post getPostById(@PathVariable Long id){
        if (id == 1){
        return new Post(1, "My first post", "1asdfasdfasdfasdfasdfasdfasdfasdfasdf");
        }
        else{
            return null;
        }
    }


    @PostMapping
    private void createPosts(@RequestBody Post newPost){
        System.out.println(newPost.getTitle());
        System.out.println(newPost.getContent());
    }


    @PutMapping("{id}")
    private void updatePost(@PathVariable Long id){
        System.out.println("Successful edit @: " + id);
    }

    @DeleteMapping("{id}")
    private void deletePost(@PathVariable Long id){
        System.out.println("Successful delete @: " + id);
    }

}
