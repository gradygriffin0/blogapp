package com.codeup.blogapp.web;

import com.codeup.blogapp.data.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    private List<Post> Posts;


    PostsController() {
        Posts = new ArrayList<Post>() {{
            add(new Post(1, "My first post", "1asdfasdfasdfasdfasdfasdfasdfasdfasdf"));
            add(new Post(2, "My second post", "2asdfasdfasdfasdfasdfasdfasdfasdfasdf"));
            add(new Post(3, "My third post", "3asdfasdfasdfasdfasdfasdfasdfasdfasdf"));
            add(new Post(4, "My fourth post", "4asdfasdfasdfasdfasdfasdfasdfasdfasdf"));
        }};
    }

    // depending on HTTP METHOD GET POST PUT DELETE on endpoint /api/posts -> the method with that annotation fires
    @GetMapping()
    private List<Post> getPosts() {
        return Posts;
    }

    @GetMapping("{id}")
    private Post getPostById(@PathVariable int id) {
        return Posts.get(id);
    }


    @PostMapping
    private void createPosts(@RequestBody Post newPost) {
        System.out.println(newPost.getTitle());
        System.out.println(newPost.getContent());

        int id = Posts.size();
        newPost.setId((long) id);
        Posts.add(newPost);

    }


    @PutMapping("{id}")
    private void updatePost(@PathVariable Post post) {
        Posts.set(Math.toIntExact(post.getId()), post);
        System.out.println("Successful edit @: " + post);
    }

    @DeleteMapping("{id}")
    private void deletePost(@PathVariable int id) {
        Posts.remove(id);
        System.out.println("Successful delete @: " + id);

    }

}
