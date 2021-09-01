package com.codeup.blogapp.web;

import com.codeup.blogapp.data.category.Category;
import com.codeup.blogapp.data.category.CategoryRepository;
import com.codeup.blogapp.data.post.Post;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("!hasAuthority('USER')")
    private List<Category> getCategories(){
        return catRepo.findAll();

    }

    @GetMapping("/{id}")
    private Category getPostsByCategory(@PathVariable Long id){

        return catRepo.findById(id).get();
    }
}
