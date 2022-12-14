package com.example.springsecirityapplication.controllers;

import com.example.springsecirityapplication.models.Person;
import com.example.springsecirityapplication.services.PersonService;
import com.example.springsecirityapplication.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final PersonService personService;

    private final PersonValidator personValidator;

    @Autowired
    public RegistrationController(PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
    }
    @GetMapping("register")
    public String registration(@ModelAttribute("person") Person person) {
        return "registration/register";
    }

    @PostMapping("/register")
    public String resultRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person,bindingResult );
        if(bindingResult.hasErrors()) {

            return "registration/register";
        }
        personService.reg(person);
        return "redirect:/index";
    }

}
