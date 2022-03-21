package com.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userService;

    //retrieve all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    //retrieve user based on id
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        return userService.findOne(id);
    }

}
