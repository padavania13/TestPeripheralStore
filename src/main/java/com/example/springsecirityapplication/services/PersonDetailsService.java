package com.example.springsecirityapplication.services;

import com.example.springsecirityapplication.models.Person;
import com.example.springsecirityapplication.repositories.PersonRepository;
import com.example.springsecirityapplication.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Получаем пользователя из таблицы по логину с формы аутентификации
        Optional<Person> personLogin = personRepository.findByUsername(username) ;
//        Optional<Person> personEmail = personRepository.findByEmail(username) ;
//        Optional<Person> personPhoneNumber = personRepository.findByPhoneNumber(username) ;

        //Если пользователь не был найден
        if(personLogin.isEmpty() ) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new PersonDetails(personLogin.get());
    }
}
