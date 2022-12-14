//package com.example.springsecirityapplication.config;
//
//import com.example.springsecirityapplication.services.PersonDetailsService;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//
//@Component
//public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {
//    private final PersonDetailsService personDetailsService;
//
//    public AuthenticationProvider(PersonDetailsService personDetailsService) {
//        this.personDetailsService = personDetailsService;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        //Получаем логин с формы аутентификации. За нас Spring Security возьмет объект из формы и передаст его сюда
//        String username = authentication.getName();
//
//        //Получаем запись найденного пользователя по логину
//        UserDetails person = personDetailsService.loadUserByUsername(username);
//
//        String password = authentication.getCredentials().toString();
//
//        if(!password.equals(person.getPassword())) {
//            throw new BadCredentialsException("Не верный пароль");
//        }
//        //Возвращаем объект аутентификации. В данном объекте будет лежать объект модели, пароль, роль(права доступа)
//        //Данный объект отправится в сессию
//        return new UsernamePasswordAuthenticationToken(person, password, Collections.emptyList());
//
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return true;
//    }
//}
