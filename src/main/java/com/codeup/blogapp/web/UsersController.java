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

    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    private List<User> UsersController(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    private User getUserById(@PathVariable Long id){
       return userRepository.findById(id).get();
    }

    @GetMapping("/findByUsername")
    private User getUserByUsername(@RequestParam String username){
        return userRepository.findFirstByUsername(username);
    }
    @GetMapping("/findByEmail")
    private User getUserByEmail(@RequestParam String email){
       return  userRepository.findFirstByEmail(email);
    }

    @PostMapping
    private void createUser(@RequestBody User newUser){

        userRepository.save(newUser);
    }

    @DeleteMapping("/{id}")
    private void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    private void deleteUser(@PathVariable Long id, @RequestBody User user){
        userRepository.save(user);
    }
    @PutMapping("/{id}/updatePassword")
    private void updatePassword
            (@PathVariable Long id, @RequestParam(required = false)
                    String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword){
        User user = userRepository.getById(id);
        user.setPassword(newPassword);
        userRepository.save(user);

    }
}
