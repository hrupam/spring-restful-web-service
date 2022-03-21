package com.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new LinkedList<>();

    static {
        users.add(new User(1, "Rupam Hari", new Date()));
        users.add(new User(2, "Quinton D'Cock", new Date()));
        users.add(new User(3, "Morgan Stanley", new Date()));
        users.add(new User(3, "Mark Henry", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        for (User u : users) if (u.getId() == user.getId()) return null;
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User u : users) if (u.getId() == id) return u;
        return null;
    }
}
