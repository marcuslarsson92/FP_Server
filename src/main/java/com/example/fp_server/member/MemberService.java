package com.example.fp_server.member;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * A class that encapsulates the business logic related to member management
 * and interacts with the MemberRepository for data access and persistence operations.
 *
 * @author Marcus Larsson
 */
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * Instantiates a new Member service.
     *
     * @param memberRepository the member repository
     */
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * Gets members.
     *
     * @return the members
     */
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    /**
     * Gets login accept.
     *
     * @param email    the email
     * @param password the password
     * @return the login accept
     */
    public boolean getLoginAccept(String email, String password) {
        String pw = memberRepository.acceptLogin(email);
        return (password.equals(pw));
    }

    /**
     * Gets login member.
     *
     * @param email the email
     * @return the login member
     */
    public Optional<Member> getLoginMember(String email) {
        return memberRepository.findMemberByEmail(email);
    }

    /**
     * Add new member.
     *
     * @param member the member
     * @return
     */
    public String addNewMember(Member member) {
        //try {
        Optional<Member> memberOptional = memberRepository.findMemberByEmail(member.getEmail());

        if (memberOptional.isPresent()) {
            //throw new IllegalStateException("email taken");
            System.out.println("email taken!!!!!!!!!!!!!!!!");
            //return new ResponseEntity<>("email taken", HttpStatus.BAD_REQUEST);
            return "email taken";
        }
        memberRepository.save(member);
        System.out.println("Member added!!!!!!!!!!!!!!!!");
        return "Member added";
        /*} catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }*/
    }

    public void deleteMember(String email) {
        memberRepository.deleteMember(email);
    }

    /**
     * Update member.
     *
     * @param email    the email
     */
    @Transactional
    public void updateEmail(String email, String newEmail) {
        memberRepository.updateEmail(email, newEmail);
    }

        /*
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalStateException(
                "member with id " + memberId + " does not exist"));

        if (email != null && email.length() > 0 && !Objects.equals(member.getEmail(), email)) {
            Optional<Member> memberOptional = memberRepository.findMemberByEmail(email);
            if (memberOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            member.setEmail(email);

        }
          if (name != null && name.length() > 0 && !Objects.equals(member.getFirstName(), name)) {
            member.setFirstName(name);
        }

         */

    @Transactional
    public void updatePassword(String email, String newPassword) {
        memberRepository.updatePassword(email, newPassword);
    }
}
