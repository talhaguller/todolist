package com.talhaguller.todolist.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank
    @Size(min = 3,max = 20)
    private String username;

    @NotBlank
    @Size(min = 6,max = 40)
    private String pasasword;

    @NotBlank
    @Email
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasasword() {
        return pasasword;
    }

    public void setPasasword(String pasasword) {
        this.pasasword = pasasword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
