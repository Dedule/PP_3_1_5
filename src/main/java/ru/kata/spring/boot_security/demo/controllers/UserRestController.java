package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.exception_handling.UserNotFoundException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userServices;

    public UserRestController(UserService userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userServices.getUserList();
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return userServices.getAllRoles();
    }

    @GetMapping("/users/auth")
    public User getAuthUser(Principal principal) {
        return userServices.findByEmail(principal.getName());
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        User user = userServices.getUser(id);
        if (user == null) {
            throw new UserNotFoundException("There is no user with id = " + id + " in database");
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
        userServices.addUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        userServices.update(id, user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        userServices.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
