package com.example.fp_server.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class is a controller class in a Java Spring Boot application
 * that handles HTTP requests related to posts. It is responsible for
 * receiving requests, processing them, and returning responses.
 *
 * @author Marcus Larsson
 */
@RestController
@RequestMapping(path = "api/v1/post")
public class PostController {
    private final PostService postService;

    /**
     * Instantiates a new Post controller.
     *
     * @param postService the post service
     *
     * @author Marcus Larsson
     */
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Post new post.
     *
     * @param post the post
     */
    @PostMapping(path = "/new")
    public void postNewPost(@RequestBody Post post) {
        post.setDate(LocalDateTime.now());
        postService.addNewPost(post);
    }

    /**
     * Gets posts by email.
     *
     * @param email the email
     * @return the posts by email
     */
    @GetMapping(path = "/{email}")
    public List<Post> getPostsByEmail(@PathVariable("email") String email) {
        return postService.getPostsByEmail(email);
    }

    /**
     * Gets all posts.
     *
     * @return the all posts
     */
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
}
