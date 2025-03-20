
package com.OnlineLibrary.Controllers;

import com.OnlineLibrary.Entities.sql.User;
import com.OnlineLibrary.ErrHandling.EmailAlreadyExistsException;
import com.OnlineLibrary.ErrHandling.UserNotFoundException;
import com.OnlineLibrary.Repository.sql.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException(user.getEmail());
        }
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String email) {
        return ResponseEntity.ok(userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email)));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        userRepository.delete(user);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PutMapping("/{email}")
    public ResponseEntity<User> updateUser(
            @PathVariable String email,
            @RequestBody User updatedUser
    ) {

        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        // name update only to avoid conflicts with unique email
        existingUser.setName(updatedUser.getName());
        return ResponseEntity.ok(userRepository.save(existingUser));
    }
}