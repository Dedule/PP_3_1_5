package ru.kata.spring.boot_security.demo.services;


import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();

    void update(Long id, User updUser);

    User findByEmail(String email);

    void delete(Long id);

    List<Role> getAllRoles();

    void addUser(User user);
}
