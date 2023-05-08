package com.example.fp_server.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void addNewPost(Post post) {
        postRepository.save(post);
    }

    public List<Post> getPostsByEmail(String email){
        return postRepository.getPostsByEmail(email);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
