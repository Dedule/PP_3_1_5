package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.reposirories.RoleRepository;
import ru.kata.spring.boot_security.demo.reposirories.UserRepository;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder pe;
    private final RoleRepository roleRepository;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder pe, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.pe = pe;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void update(Long id, User updUser) {
        User user1 = userRepository.getReferenceById(id);
        user1.setEmail(updUser.getEmail());
        user1.setFirstName(updUser.getFirstName());
        user1.setLastName(updUser.getLastName());
        user1.setAge(updUser.getAge());
        user1.setPassword(pe.encode(updUser.getPassword()));
        user1.setRoles(updUser.getRoles());
        userRepository.save(user1);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        user.setPassword(pe.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findUserById(id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
