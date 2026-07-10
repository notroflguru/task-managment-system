package com.tms.users.model;

import jakarta.persistence.*;

@Table(name = "users")
@Entity
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private Role role;

    public UserEntity() {
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
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String passwordHash) {
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
