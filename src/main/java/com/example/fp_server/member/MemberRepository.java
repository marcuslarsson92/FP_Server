package com.example.fp_server.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

/**
 * The annotation @Repository can be applied to a class that provides
 * data access through a database, a file, or another external data storage
 * technology. By marking a class with @Repository, you inform the Spring Framework
 * to handle the instantiation of the class and to use it as a data access component.
 *
 * @author Marcus Larsson
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

    @Modifying
    @Query("DELETE FROM Member m WHERE m.email = :email")
    void deleteMember(@Param("email") String email);



}
