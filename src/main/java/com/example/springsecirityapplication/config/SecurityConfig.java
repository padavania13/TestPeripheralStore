package com.example.springsecirityapplication.config;

import com.example.springsecirityapplication.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity//указывает что данный класс является конфигурационным файлом для Spring Security
//public class SecurityConfig extends WebSecurityConfiguration {
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private final AuthenticationProvider authenticationProvider;
//
//    @Autowired
//    public SecurityConfig(AuthenticationProvider authenticationProvider) {
//        this.authenticationProvider = authenticationProvider;
//    }
@Override
public void configure(WebSecurity web)
{
    web.ignoring()
            .antMatchers("/resources/", "/static/", "/css/", "/js/", "/img/", "/icon/");
}
    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                //указываем что все страницы должны быть защищены аутентификацией
                .authorizeRequests()
                //на страницу /admin может попасть только админинистратор
                .antMatchers("/admin").hasRole("ADMIN")
                //с помощью permitAll указываем указанные страницы доступны всем пользователям
                .antMatchers("/authentication/login", "/registration/register", "/error", "/css/**", "/img/**","/css/", "/img/","/footer", "/header", "/index", "/product","/product/info/{id}", "/product/search").permitAll()
                // Указываем что все остальные страницы доступны пользователям с ролью user и admin
                .anyRequest().hasAnyRole("USER", "ADMIN")
//                // Указываем что для всех остальных страниц необходимо вызывать метод
//authenticated(), который открывает форму аутентификации
//                .anyRequest().authenticated()
                .and()
                //указываем какой url запрос будет отправляться при заходе на защищенные страницы
                .formLogin().loginPage("/authentication/login")
                // указываем на какой url будут отправляться данные с формы аутентификации
                .loginProcessingUrl("/process_login")
                // Указываем на какой url необходимо направить пользователя после успешной аутентификации
                .defaultSuccessUrl("/index", true)
                // Указываем куда необходимо отправить при неверной аутентификации
                .failureUrl("/authentication/login?error")
        .and()
                //Указываем куда перейдум при нажатии на ссылку которая ведет на страницу /logout
                .logout().logoutUrl("/logout").logoutSuccessUrl("/authentication/login");

    }

    //Данный метод позволяет настроить аутентификацию
    protected void configure (AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        authenticationManagerBuilder.userDetailsService(personDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
