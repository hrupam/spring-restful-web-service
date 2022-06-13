package com.rest.webservices.restfulwebservices.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/")
public class HomeResource {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("")
    public String helloWorldInternationalised(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("hello.world.message", null, "Language not available", LocaleContextHolder.getLocale());
    }
}
