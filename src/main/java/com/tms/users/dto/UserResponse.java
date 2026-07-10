package com.tms.users.dto;

import com.tms.users.model.Role;

public class UserResponse {
    private Long id;
    private String login;
    private String email;
    private String name;
    private Role role;

    // Геттеры
    public Long getId() {return id;}
    public String getLogin() {
        return login;
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
    public void setId(Long id) {this.id = id;}
    public void setLogin(String login) {
        this.login = login;
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
