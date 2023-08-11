package com.example.fp_server.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

/**
 * Annoteringen @Repository kan tillämpas på en klass som tillhandahåller dataåtkomst
 *  via en databas, en fil eller en annan extern datalagringsteknik. Genom att märka
 *  en klass med @Repository, berättar du för Spring Framework att den ska hantera
 *  instansieringen av klassen och att den ska användas som en dataåtkomstkomponent.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT s FROM Member s WHERE s.email = ?1") //write a query and use the following method findMemberByEmail to
    Optional<Member> findMemberByEmail(String email); //call this query and return an Optional<Member>

    @Query("SELECT password FROM Member s WHERE s.email = ?1")
    String acceptLogin(String email);

    @Modifying
    @Query("UPDATE Member m SET m.email = :newEmail WHERE m.email = :email")
    void updateEmail(@Param("email") String email, @Param("newEmail") String newEmail);

    @Modifying
    @Query("UPDATE Member m SET m.password = :newPassword WHERE m.email = :email")
    void updatePassword(@Param("email") String email, @Param("newPassword") String newPassword);
}
