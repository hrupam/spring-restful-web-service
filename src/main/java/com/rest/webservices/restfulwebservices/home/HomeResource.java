package com.rest.webservices.restfulwebservices.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeResource {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("")
    public String helloWorldInternationalised() {
        return messageSource.getMessage("hello.world.message", null, "Language not available", LocaleContextHolder.getLocale());
    }
}
