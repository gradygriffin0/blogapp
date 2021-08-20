package com.codeup.blogapp.web;

import com.codeup.blogapp.data.category.Category;
import com.codeup.blogapp.data.post.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value= "/api/categories", headers="Accept=application/json")
public class CategoriesController {

    List<Category> categories = new ArrayList<>();
    List<Post> posts = new ArrayList<>();

    @GetMapping
    private List<Category> getCategories(){
        posts = new ArrayList<Post>() {{
            add(new Post("Family post", "1asdfasdfasdfasdfasdfasdfasdfasdfasdf"));
            add(new Post("My Family stuff post", "2asdfasdfasdfasdfasdfasdfasdfasdfasdf"));
        }};
        categories.add(new Category(1, "Family"));
        categories.get(0).setPosts(posts);

        categories.add(new Category(2, "Friends"));
        categories.add(new Category(3, "Food"));
        categories.add(new Category(4, "Travel"));

        return categories;

    }

    @GetMapping("/postsByCategory")
    private List<Post> getPostsByCategory(@RequestParam Category category){

        if (category.getPosts() != null){
            return (List<Post>) category.getPosts();
        }else {
            return null;
        }
    }
}
