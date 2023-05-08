package com.example.fp_server.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(path = "/new")
    public void postNewPost(@RequestBody Post post) {
        post.setDate(LocalDateTime.now());
        postService.addNewPost(post);
    }

    @GetMapping(path = "/{email}")
    public List<Post> getPostsByEmail(@PathVariable("email") String email) {
        return postService.getPostsByEmail(email);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
}
