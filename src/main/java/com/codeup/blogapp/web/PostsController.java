package com.codeup.blogapp.web;

import com.codeup.blogapp.data.Category;
import com.codeup.blogapp.data.Post;
import com.codeup.blogapp.data.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    private List<Post> posts;
    List<Category> categories = new ArrayList<>();


     public PostsController() {
        User user = new User(1l, "teste", "testing@test", "password1234", posts);
        categories.add(new Category(1, "Family"));


        posts = new ArrayList<Post>() {{
            add(new Post(1, "My first post", "1asdfasdfasdfasdfasdfasdfasdfasdfasdf", user, categories));
            add(new Post(2, "My second post", "2asdfasdfasdfasdfasdfasdfasdfasdfasdf", user, categories));
            add(new Post(3, "My third post", "3asdfasdfasdfasdfasdfasdfasdfasdfasdf", user, categories));
            add(new Post(4, "My fourth post", "4asdfasdfasdfasdfasdfasdfasdfasdfasdf", user, categories));
        }};
        System.out.println(posts.size());
        for (int i = 0; i < posts.size(); i++) {
            System.out.println(posts.get(i));
        }
        for (Post post : posts){
            System.out.println(post.getId());
        }
    }

    // depending on HTTP METHOD GET POST PUT DELETE on endpoint /api/posts -> the method with that annotation fires
    @GetMapping()
    private List<Post> getPosts() {

        return posts;
    }

    @GetMapping("{id}")
    private Post getPostById(@PathVariable int id) {
        return posts.get(id);
    }


    @PostMapping
    private void createPosts(@RequestBody Post newPost) {
        System.out.println(newPost.getTitle());
        System.out.println(newPost.getContent());

        int id = posts.size();
        newPost.setId((long) id);
        posts.add(newPost);

    }


    @PutMapping("/{id}")
    private void updatePost(@PathVariable long id, @RequestBody Post post) {


        System.out.println("Successful edit @: " + id);
    }

    @DeleteMapping("/{id}")
    private void deletePost(@PathVariable int id) {
        posts.remove(id);
        System.out.println("Successful delete @: " + id);

    }

}
