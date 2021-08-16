package com.codeup.blogapp.web;

import com.codeup.blogapp.data.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UsersController {
    

    @GetMapping()
    private List<User> UsersController(){

        return new ArrayList<User>(){{
            add(new User(1, "Gradman", "email", "password", new Date(), User.Role.ADMIN));
            add(new User(2, "Casey", "email2", "password2", new Date(), User.Role.USER));
            add(new User(3, "Laura", "email3", "password3", new Date(), User.Role.USER));
        }};
    }

    @GetMapping("/{id}")
    private void getUserById(@PathVariable int id){
        System.out.println("GET: " + id);
    }

    @GetMapping("/{username}")
    private void getUserByUsername(@PathVariable String username){
        System.out.println(username);
    }
    @GetMapping("/{email}")
    private void getUserByEmail(@PathVariable String email){
        System.out.println(email);
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
}
