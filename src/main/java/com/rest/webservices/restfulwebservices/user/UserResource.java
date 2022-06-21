package com.rest.webservices.restfulwebservices.user;

import com.rest.webservices.restfulwebservices.post.Post;
import com.rest.webservices.restfulwebservices.post.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    //create a user
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    //retrieve a user based on id
    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id) {
        User user = userService.findOne(id);
        if (user == null)
            throw new UserNotFoundException("User not found for id = " + id);

        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User deletedUser = userService.deleteUser(id);
        if (deletedUser == null) throw new UserNotFoundException("User not found for id = " + id);
    }


    //retrieve all posts for a user based on id
    @GetMapping("/users/{id}/posts")
    public List<Post> getAllPostsForUser(@PathVariable int id) {
        User user = userService.findOne(id);
        if (user == null)
            throw new UserNotFoundException("User not found for id = " + id);
        return user.getPosts();
    }

    //create a post for a user
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
        Post savedPost = userService.savePost(id, post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{postId}").buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    //retrieve a post of a user based on post id
    @GetMapping("/users/{userId}/posts/{postId}")
    public Post getPostForUser(@PathVariable int userId, @PathVariable int postId) {
        Post post = userService.findPostByUser(userId, postId);
        if (post == null)
            throw new PostNotFoundException("Post not found for id = " + postId + " having userId = " + userId);
        return post;
    }

    //delete a post for a user
    @DeleteMapping("/users/{userId}/posts/{postId}")
    public void deletePostForUser(@PathVariable int userId, @PathVariable int postId) {
        Post deletedPost = userService.deletePostByUser(userId, postId);
        if (deletedPost == null)
            throw new PostNotFoundException("Post not found for id = " + postId + " having userId = " + userId);
    }
}
