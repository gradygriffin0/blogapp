package com.codeup.blogapp.data.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByUsername(String username);
    User findFirstByEmail(String email);

    Optional<User> findByEmail(String email);


}
