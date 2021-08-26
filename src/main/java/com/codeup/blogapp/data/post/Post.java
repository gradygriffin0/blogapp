package com.codeup.blogapp.data.post;

import com.codeup.blogapp.data.category.Category;
import com.codeup.blogapp.data.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @Column(nullable = false, length = 2000)
    private String content;

    @ManyToOne
    @JsonIgnoreProperties({"posts","password"})
    private User user;

    @ManyToMany(fetch =  FetchType.LAZY,
            cascade = { CascadeType.DETACH, CascadeType.REFRESH}, targetEntity = Category.class)
    @JoinTable(
            name="post_category",
            joinColumns = {@JoinColumn(name = "post_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="category_id", nullable = false, updatable = false)},
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT)
    )
    @JsonIgnoreProperties("posts")
    private Collection<Category> categories;

    public Post() {

    }

    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    public Post(Long id, String title, String content, User user, Collection<Category> categories) {
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

    public Long getId() {
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
