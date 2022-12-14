package com.example.springsecirityapplication.services;

import com.example.springsecirityapplication.models.Person;
import com.example.springsecirityapplication.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //Метод по поиску пользователя по username
    public Person findByUsername(Person person) {
        Optional<Person> person_db = personRepository.findByUsername(person.getUsername());
        return person_db.orElse(null);
    }

    @Transactional
    public void reg(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        personRepository.save(person);
    }



}
