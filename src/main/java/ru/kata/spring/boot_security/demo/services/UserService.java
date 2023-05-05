package ru.kata.spring.boot_security.demo.services;



import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();

    User show(Long id);

    void update(Long id, User updUser);

    User findByUsername(String username);

    void delete(Long id);
}
