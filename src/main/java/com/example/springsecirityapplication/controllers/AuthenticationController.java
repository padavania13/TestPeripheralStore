package com.example.springsecirityapplication.controllers;

import com.example.springsecirityapplication.models.Person;
import com.example.springsecirityapplication.services.PersonService;
import com.example.springsecirityapplication.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/authentication")
public class AuthenticationController {

    private final PersonService personService;
    private final PersonValidator personValidator;

    @Autowired
    public  AuthenticationController(PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
    }

    @GetMapping("/login")
    public String login() {

        return "authentication/login";
    }

//    @GetMapping("/registration")
//    public String registration(@ModelAttribute("person") Person person) {
//        return "authentication/registration";
//    }
//
//    @PostMapping("/registration")
//    public String resultRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
//        personValidator.validate(person,bindingResult );
//        if(bindingResult.hasErrors()) {
//
//            return "authentication/registration";
//        }
//        personService.register(person);
//        return "redirect:/index";
//    }

}
