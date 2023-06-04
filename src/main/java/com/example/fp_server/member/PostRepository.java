package com.example.fp_server.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The annotation @Repository can be applied to a class that provides data access
 * through a database, a file, or another external data storage technology. By
 * marking a class with @Repository, you tell the Spring Framework to handle
 * the instantiation of the class and to use it as a data access component.
 *
 * @author Marcus Larsson
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT s FROM Post s WHERE s.email = ?1 order by id desc")
    List<Post> getPostsByEmail(String email);

    @Query("SELECT s FROM Post s order by id DESC")
    List<Post> getAllPosts();
}
