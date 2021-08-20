package com.codeup.blogapp.data.category;

import com.codeup.blogapp.data.post.Post;

import java.util.Collection;

public class Category {
    private long id;
    private String name;
    private Collection<Post> posts;

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
