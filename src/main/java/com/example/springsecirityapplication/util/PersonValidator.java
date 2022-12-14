package com.example.springsecirityapplication.util;

import com.example.springsecirityapplication.models.Person;

import com.example.springsecirityapplication.services.PersonService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    //В данном методе прописываем правила валидации
    @Override
    public void validate(Object target, Errors errors) {
        //Object target преобразуем в объект класса Person
        Person person = (Person)target;
        if(personService.findByUsername(person) !=null) {
            errors.rejectValue("username", "", "Логин занят");
        }
    }
}
