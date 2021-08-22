package com.codeup.blogapp.web;

import com.codeup.blogapp.data.category.Category;
import com.codeup.blogapp.data.category.CategoryRepository;
import com.codeup.blogapp.data.post.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value= "/api/categories", headers="Accept=application/json")
public class CategoriesController {

    private final CategoryRepository catRepo;

    public CategoriesController(CategoryRepository catRepo){
        this.catRepo = catRepo;
    }

    @GetMapping
    private List<Category> getCategories(){
        return catRepo.findAll();

    }

    @GetMapping("/postsByCategory")
    private List<Post> getPostsByCategory(@RequestParam Category category){

        return (List<Post>) category.getPosts();
    }
}
