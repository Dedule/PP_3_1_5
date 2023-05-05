package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService us;

    public UserController(UserService us) {
        this.us = us;
    }

    @GetMapping("/admin")
    public String index(@ModelAttribute("user") User user, Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("usersList", us.getUserList());
        return "admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", us.show(id));
        return "/edit";
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        us.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/edit/{id}")
    public String delete(@PathVariable("id") Long id) {
        us.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String userPage(Principal principal, Model model){
        model.addAttribute("username", principal.getName());
        return "/user";
    }
    @DeleteMapping("/user")
    public String deleteUser(Principal principal) {
        User user = us.findByUsername(principal.getName());
        us.delete(user.getId());
        return "redirect:/auth/login";
    }
}
