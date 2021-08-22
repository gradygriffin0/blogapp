package com.codeup.blogapp.web;

import com.codeup.blogapp.data.post.Post;
import com.codeup.blogapp.data.user.User;
import com.codeup.blogapp.data.user.UserRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UsersController {

    private final UserRepository userRepositroy;

    public UsersController(UserRepository userRepository) {
        this.userRepositroy = userRepository;
    }

    @GetMapping()
    private List<User> UsersController(){
        return userRepositroy.findAll();
    }

    @GetMapping("/{id}")
    private User getUserById(@PathVariable Long id){
       return userRepositroy.getById(id);
    }

    @GetMapping("/findByUsername")
    private User getUserByUsername(@RequestParam String username){
        return userRepositroy.findFirstByUsername(username);
    }
    @GetMapping("/findByEmail")
    private User getUserByEmail(@RequestParam String email){
       return  userRepositroy.findFirstByEmail(email);
    }

    @PostMapping
    private void createUser(@RequestBody User newUser){

        userRepositroy.save(newUser);
    }

    @DeleteMapping("/{id}")
    private void deleteUser(@PathVariable Long id){
        userRepositroy.deleteById(id);
    }

    @PutMapping("/{id}")
    private void deleteUser(@PathVariable Long id, @RequestBody User user){
        userRepositroy.save(user);
    }
    @PutMapping("/{id}/updatePassword")
    private void updatePassword
            (@PathVariable Long id, @RequestParam(required = false)
                    String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword){
        User user = userRepositroy.getById(id);
        user.setPassword(newPassword);
        userRepositroy.save(user);

    }
}
