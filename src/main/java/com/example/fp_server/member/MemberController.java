package com.example.fp_server.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<Member> getMembers() {
        return memberService.getMembers();
    }

    @GetMapping(path = "/{email}/{password}")
    public boolean getLoginAccept(@PathVariable("email") String email, @PathVariable("password") String password) {
        return memberService.getLoginAccept(email, password);
    }

    @GetMapping(path = "/{email}")
    public Optional<Member> getLoginMember(@PathVariable ("email") String email) {
        return memberService.getLoginMember(email);
    }


    @PostMapping
    public void registerNewMember(@RequestBody Member member) {
        memberService.addNewMember(member);
    }
    @DeleteMapping(path = "/{memberId}")
    public void deleteMember(@PathVariable("memberId") Long memberId) {
        memberService.deleteMember(memberId);
    }
    @PutMapping(path = "/{memberId}")
    public void updateMember(
            @PathVariable("memberId") Long memberId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
            memberService.updateMember(memberId, name, email);
    }

}
