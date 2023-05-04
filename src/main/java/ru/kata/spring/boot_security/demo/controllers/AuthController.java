package ru.kata.spring.boot_security.demo.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RegService;
import ru.kata.spring.boot_security.demo.util.PersonValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PersonValidator validator;
    private final RegService regService;

    public AuthController(PersonValidator validator, RegService regService) {
        this.validator = validator;
        this.regService = regService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }
    @GetMapping("/reg")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "auth/reg";
    }

    @PostMapping("/reg")
    public String performReg(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        validator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return "/auth/reg";
        regService.register(user);
        return "redirect:/auth/login";
    }
}
