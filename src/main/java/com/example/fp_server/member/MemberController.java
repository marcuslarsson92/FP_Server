package com.example.fp_server.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The @RestController annotation is used to indicate that the class is a RESTful web service
 * controller that handles HTTP requests and responses. This means that the controller will
 * take care of processing incoming requests, performing the necessary operations, and returning
 * a response to the client.
 */
@RestController
@RequestMapping(path = "api/v1/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    /**
     * Sends the list of members to the client (webpage)
     * @return
     */

    @GetMapping
    public List<Member> getStudents() {
        return memberService.getMembers();
    }

    @GetMapping(path = "/{email}/{password}")
    public boolean getLoginAccept(@PathVariable("email") String email, @PathVariable("password") String password) {
        return memberService.getLoginAccept(email, password);
    }


    @PostMapping
    public void registerNewMember(@RequestBody Member member) {
        memberService.addNewMember(member);
    }
    @DeleteMapping(path = "/{memberId}")
    public void deleteMember(@PathVariable("memberId") Long memberId) {
        memberService.deleteMember(memberId);
    }

    /**
     * Update member.
     * @param email    the current email
     * @param newEmail the new email
     */
    @PutMapping(path = "/email}")
    public void updateEmail(
            @PathVariable("email") String email,
            @RequestParam(required = false) String newEmail) {
            memberService.updateEmail(email, newEmail);

    }

}
