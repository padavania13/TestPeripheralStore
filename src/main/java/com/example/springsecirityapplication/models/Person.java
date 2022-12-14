package com.example.springsecirityapplication.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message="Обязательно к заполнению")
    @Size(min = 3, max = 20, message = "Логин должен быть от 3 до 20 символов")
//    @Min(value=2, message="Логин должен содержать минимум 2 символа")
//    @Max(value=30, message="Логин может содержать максимум 30 символов")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_\\.]{2,20}$", message =  "Логин должен содержать буквы и цифры, первый символ обязательно буква")
    @Column(name = "username")
    private String username;
    @NotEmpty(message="Обязательно к заполнению")
//    @Size(min = 3, max = 20, message = "Пароль должен быть от 6 до 20 символов")
//    @Min(value=2, message="Пароль должен содержать минимум 2 символа")
//    @Max(value=30, message="Пароль может содержать максимум 30 символов")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$", message =  "Пароль должен содержать строчные и прописные латинские буквы, цифры")
    @Column(name = "password")
    private String password;

    @Column(name="role")
    private String role;

    @ManyToMany()
    @JoinTable(name = "product_cart", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> product;

    public Person() {
    }

    public Person(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
