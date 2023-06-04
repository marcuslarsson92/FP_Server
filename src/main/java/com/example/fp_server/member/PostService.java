package com.example.fp_server.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A class that encapsulates the business logic related to post management
 * and interacts with the PostRepository for data access and persistence
 * operations. It provides methods to create new posts, retrieve posts by
 * email, and retrieve all posts.
 */
@Service
public class PostService {

    private final PostRepository postRepository;

    /**
     * Instantiates a new Post service.
     *
     * @param postRepository the post repository
     */
    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Add new post.
     *
     * @param post the post
     */
    public void addNewPost(Post post) {
        postRepository.save(post);
    }

    /**
     * Get posts by email list.
     *
     * @param email the email
     * @return the list
     */
    public List<Post> getPostsByEmail(String email){
        return postRepository.getPostsByEmail(email);
    }

    /**
     * Gets all posts.
     *
     * @return the all posts
     */
    public List<Post> getAllPosts() {
        return postRepository.getAllPosts();
    }
}
