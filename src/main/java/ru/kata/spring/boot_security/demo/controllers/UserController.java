package ru.kata.spring.boot_security.demo.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.util.PersonValidator;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService us;
    private final PersonValidator validator;

    public UserController(UserService us, PersonValidator validator) {
        this.us = us;
        this.validator = validator;
    }

    @GetMapping("/user")
    public String userPage(Principal principal, Model model) {
        User user = us.findByEmail(principal.getName());
        model.addAttribute("authUser", user);
        return "/user";
    }

    @GetMapping("/admin")
    public String index(@ModelAttribute("user") User user, Model model, Principal principal) {
        User user1 = us.findByEmail(principal.getName());
        model.addAttribute("authUser", user1);
        model.addAttribute("usersList", us.getUserList());
        model.addAttribute("allRoles", us.getAllRoles());
        return "admin";
    }


    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        us.delete(id);
        return "redirect:/admin";
    }


    @PostMapping("/admin")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        validator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) return "/admin";
        us.addUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/admin/{id}")
    public String editUser(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
        us.update(id, user);
        return "redirect:/admin";
    }
}
