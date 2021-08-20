package com.codeup.blogapp.data.user;

import com.codeup.blogapp.data.post.Post;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String username;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 50)
    private String password;
//    private Date createdAt;

    private Role role = Role.USER;
    private Collection<Post> posts;

    public enum Role {USER, ADMIN};

    public User() {
    }

    public User(String username){
        this.username = username;
    }
    public User(long id, String username, String email, String password, Collection<Post> posts /*Date createdAt*/) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.posts = posts;
//        this.createdAt = createdAt;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
*/
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}