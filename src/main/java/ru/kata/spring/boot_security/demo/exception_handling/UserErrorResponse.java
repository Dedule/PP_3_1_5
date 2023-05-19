package ru.kata.spring.boot_security.demo.exception_handling;

public class UserErrorResponse {
    private String massage;

    public UserErrorResponse(String massage) {
        this.massage = massage;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
