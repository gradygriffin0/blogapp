package com.codeup.blogapp.data.post;

import com.codeup.blogapp.data.category.Category;
import com.codeup.blogapp.data.user.User;

import javax.persistence.*;
import java.util.Collection;

//using jpa we can label this POJO @entity and give it a @table name
@Entity
@Table(name = "posts")
public class Post {
    // @id/strategy = identify means primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // insert columns
    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String content;


    private User user;


    private Collection<Category> categories;

    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    public Post(long id, String title, String content, User user, Collection<Category> categories) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.categories = categories;

    }
    public Post(String title, String content){
        this.title = title;
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}