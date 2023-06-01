package com.example.fp_server.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Annoteringen @Repository kan tillämpas på en klass som tillhandahåller dataåtkomst
 *  via en databas, en fil eller en annan extern datalagringsteknik. Genom att märka
 *  en klass med @Repository, berättar du för Spring Framework att den ska hantera
 *  instansieringen av klassen och att den ska användas som en dataåtkomstkomponent.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT s FROM Post s WHERE s.email = ?1")
    List<Post> getPostsByEmail(String email);

    @Query("SELECT s FROM Post s order by id DESC")
    List<Post> getAllPosts();
}
