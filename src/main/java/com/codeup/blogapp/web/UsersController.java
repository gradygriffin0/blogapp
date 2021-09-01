package com.codeup.blogapp.web;

import com.codeup.blogapp.data.post.Post;
import com.codeup.blogapp.data.user.User;
import com.codeup.blogapp.data.user.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UsersController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping()
    @PreAuthorize("!hasAuthority('USER')")
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

    @PostMapping("/create")
    private void createUser(@RequestBody User newUser){
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
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
