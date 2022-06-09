package com.rest.webservices.restfulwebservices.user;

import com.rest.webservices.restfulwebservices.post.Post;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new LinkedList<>();
    private static int userCounter = 4;
    private static int postCount = 4;

    static {
        Post p1 = new Post(1, "I am at Bangkok", "enjoying!!!");
        Post p2 = new Post(2, "I got a new job", "new journey begins!!!");
        Post p3 = new Post(3, "I am a good boy", "happy!!!");
        Post p4 = new Post(4, "Got 5 🌟 @Leetcode", "proud moment!!!");


        users.add(new User(1, "Rupam Hari", new Date(), new ArrayList<>(List.of(p1, p2))));
        users.add(new User(2, "Quinton D'Cock", new Date(), List.of(p3)));
        users.add(new User(3, "Morgan Stanley", new Date(), List.of(p4)));
        users.add(new User(4, "Mark Henry", new Date(), new ArrayList<>()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        user.setId(++userCounter);
        if (user.getPosts() == null) user.setPosts(new ArrayList<>());
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public User deleteUser(int id) {
        User user = this.findOne(id);
        if (user != null) users.remove(user);
        return user;

    }

    public Post savePost(int userId, Post post) {
        User user = findOne(userId);
        if (user == null) throw new UserNotFoundException("User not found for id = " + userId);
        post.setId(++postCount);
        user.getPosts().add(post);
        return post;
    }
}
