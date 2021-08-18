package com.codeup.blogapp.web;

import com.codeup.blogapp.data.Post;
import com.codeup.blogapp.data.User;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UsersController {

    private List<User> users;

    public UsersController(List<User> users) {

        List<Post> posts = new ArrayList<>() {{
            add(new Post( "My first post", "1asdfasdfasdfasdfasdfasdfasdfasdfasdf"));
            add(new Post( "My second post", "2asdfasdfasdfasdfasdfasdfasdfasdfasdf" ));
            add(new Post( "My third post", "3asdfasdfasdfasdfasdfasdfasdfasdfasdf"));
            add(new Post( "My fourth post", "4asdfasdfasdfasdfasdfasdfasdfasdfasdf"));
        }};

        this.users = new ArrayList<User>(){{
            add(new User(1, "Gradman", "email1", "password1", posts));
            add(new User(2, "sadman", "email2", "password2", posts));
            add(new User(3, "badman", "email3", "password3", posts));
            add(new User(4, "gladman", "email4", "password4", posts));

        }};
    }

    @GetMapping()
    private List<User> UsersController(){
        return users;
    }

    @GetMapping("/{id}")
    private User getUserById(@PathVariable long id){
        for(User user : users){
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }

    @GetMapping("/findByUsername")
    private User getUserByUsername(@RequestParam String username){
        for(User user : users){
            if (user.getUsername().equalsIgnoreCase(username)){
                return user;
            }
        }
        return null;
    }
    @GetMapping("/findByEmail")
    private User getUserByEmail(@RequestParam String email){
        for(User user : users){
            if (user.getEmail().equalsIgnoreCase(email)){
                return user;
            }
        }
        return null;
    }

    @PostMapping
    private void createUser(@RequestBody User newUser){

        System.out.println(newUser.getUsername());
        System.out.println(newUser.getEmail());
    }

    @PutMapping("/{id}")
    private void deleteUser(@PathVariable int id, @RequestBody User user){
        System.out.println("PUT: " + id);
        System.out.println(user.getUsername());
    }

    @DeleteMapping("/{id}")
    private void deleteUser(@PathVariable int id){
        System.out.println("DELETE: " + id);
    }
    @PutMapping("/{id}/updatePassword")
    private void updatePassword
            (@PathVariable long id, @RequestParam(required = false)
                    String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword){



    }
}
