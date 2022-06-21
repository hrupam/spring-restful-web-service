package com.rest.webservices.restfulwebservices.user;

import com.rest.webservices.restfulwebservices.post.Post;
import com.rest.webservices.restfulwebservices.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDaoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findOne(int id) {
        final Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User deleteUser(int id) {
        var user = this.findOne(id);
        if (user == null) return null;
        userRepository.deleteById(id);
        return user;
    }

    public Post savePost(int id, Post post) {
        var user = this.findOne(id);
        if (user == null) throw new UserNotFoundException("User not found for id = " + id);

        post.setUser(user);

        return postRepository.save(post);
    }

    public Post findPostByUser(int userId, int postId) {
        var user = this.findOne(userId);
        if (user == null) throw new UserNotFoundException("User not found for id = " + userId);
        final Optional<Post> post = user.getPosts().stream().filter(p -> p.getId() == postId).findFirst();
        return post.orElse(null);
    }

    public Post deletePostByUser(int userId, int postId) {
        var post = this.findPostByUser(userId, postId);
        if (post == null) return null;
        postRepository.delete(post);
        return post;
    }
}
