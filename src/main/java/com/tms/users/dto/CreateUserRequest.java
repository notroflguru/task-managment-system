package com.tms.users.dto;

import com.tms.users.model.Role;
import jakarta.validation.constraints.*;

public class CreateUserRequest {

    @NotBlank(message = "Login should not be blank")
    @Size(min = 5, max = 20, message = "Length must be between 5 and 20 characters")
    private String login;

    @NotBlank(message = "Password should not be blank")
    @Size(min = 6, message = "Password must contain at least 6 characters")
    private String password;

    @NotBlank(message = "Email should not be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Name should not be blank")
    @Size(max = 30, message = "Name must not exceed 30 characters")
    private String name;

    @NotNull(message = "Role is required")
    private Role role;

    // Геттеры
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public Role getRole() {
        return role;
    }

    // Сеттеры
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRole(Role role) {
        this.role = role;
    }

}
