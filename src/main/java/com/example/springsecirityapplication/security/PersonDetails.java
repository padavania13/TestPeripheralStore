package com.example.springsecirityapplication.security;

import com.example.springsecirityapplication.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class PersonDetails implements UserDetails {

    private final Person person;
    @Autowired
    public PersonDetails(Person person) {
        this.person = person;
    }

    //при наследовании интерфейса мы должны реализовать все его методы
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(person.getRole()));
    }

    @Override
    public String getPassword() {
        return  this.person.getPassword();
    }

    @Override
    public String getUsername() {
        return this.person.getUsername();
    }
    //Аккааунт действительный
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //Аккаунт не заблокирован
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //Пароль действительный
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //Аккаунт активен
    @Override
    public boolean isEnabled() {
        return true;
    }

    // Получить объект пользователя
    public Person getPerson(){
        return this.person;
    }
}
