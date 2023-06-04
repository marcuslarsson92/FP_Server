package com.example.fp_server.member;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
     */
    public void addNewMember(Member member) {
        Optional<Member> memberOptional = memberRepository.findMemberByEmail(member.getEmail());

        if (memberOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        memberRepository.save(member);
    }

    /**
     * Delete member.
     *
     * @param memberId the member id
     */
    public void deleteMember(Long memberId) {
        boolean exist = memberRepository.existsById(memberId);

        if (!exist) {
            throw new IllegalStateException("Member with id " + memberId + " does not exist");
        }
        memberRepository.deleteById(memberId);
    }

    /**
     * Update member.
     *
     * @param memberId the member id
     * @param name     the name
     * @param email    the email
     */
    @Transactional
    public void updateMember(Long memberId, String name, String email) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalStateException(
                "member with id " + memberId + " does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(member.getFirstName(), name)) {
            member.setFirstName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(member.getEmail(), email)) {
            Optional<Member> memberOptional = memberRepository.findMemberByEmail(email);
            if (memberOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            member.setEmail(email);

        }
    }
}
