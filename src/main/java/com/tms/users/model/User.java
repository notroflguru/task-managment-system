package com.tms.users.model;


public class User {

    private Long id;
    private String login;
    private String passwordHash;
    private String email;
    private String name;
    private Role role;

    public User(Long id, String login, String passwordHash, String email, String name, Role role) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    // Геттеры
    public Long getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getPasswordHash() {
        return passwordHash;
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
    public void setId(Long id) {
        this.id = id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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
