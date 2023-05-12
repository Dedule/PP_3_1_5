package ru.kata.spring.boot_security.demo.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserDetailServiceImpl;

@Component
public class PersonValidator implements Validator {
    private final UserDetailServiceImpl service;

    public PersonValidator(UserDetailServiceImpl service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        User user1 = service.findByEmail(user.getEmail());
        if (user1 != null) errors.rejectValue("email", "", "This email was already use");

    }
}
