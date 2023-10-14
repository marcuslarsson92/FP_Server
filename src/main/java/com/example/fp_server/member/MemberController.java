package com.example.fp_server.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The @RestController annotation is used to indicate that the class is a RESTful web service
 * controller that handles HTTP requests and responses. This means that the controller will
 * take care of processing incoming requests, performing the necessary operations, and returning
 * a response to the client.
 *
 * @author Marcus Larsson
 */
@RestController
@RequestMapping(path = "api/v1/member")
public class MemberController {
    private final MemberService memberService;

    /**
     * Instantiates a new Member controller.
     *
     * @param memberService the member service
     */
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

    /**
     * Gets login accept.
     *
     * @param email    the email
     * @param password the password
     * @return the login accept
     */
    @GetMapping(path = "/{email}/{password}")
    public boolean getLoginAccept(@PathVariable("email") String email, @PathVariable("password") String password) {
        return memberService.getLoginAccept(email, password);
    }

    /**
     * Gets login member.
     *
     * @param email the email
     * @return the login member
     */
    @GetMapping(path = "/{email}")
    public Optional<Member> getLoginMember(@PathVariable ("email") String email) {
        return memberService.getLoginMember(email);
    }


    /**
     * Register new member.
     *
     * @param member the member
     */
    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    @PostMapping
    public ResponseEntity<String> registerNewMember(@RequestBody Member member) {
        String output = memberService.addNewMember(member);
        if (output == "email taken") {
            return new ResponseEntity<>("email taken",HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Member added", HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/delete/{email}")
    public ResponseEntity<String> deleteMember(@PathVariable("email") String email) {
        if (email != null) {
            memberService.deleteMember(email);
            return ResponseEntity.ok("Member deleted successfully");
        }
        return ResponseEntity.badRequest().body("Invalid email.");
    }

    /**
     * Update member.
     * @param email    the current email.
     * @param member the object which we extract the new email from.
     */
    @PutMapping(path = "/{email}")
    public ResponseEntity<String> updateEmail(@PathVariable("email") String email,
            @RequestBody Member member) {

        String newEmail = member.getEmail();

        if (newEmail != null) {
            memberService.updateEmail(email, newEmail);
            return ResponseEntity.ok("Email updated successfully");
        }
        return ResponseEntity.badRequest().body("No new email provided in the JSON object.");

    }

    @PutMapping(path = "/updatepw")
    public ResponseEntity<String> updatePassword(@RequestBody Member member) {

        String email = member.getEmail();
        String newPassword = member.getPassword();

        if (email != null && newPassword != null) {
            memberService.updatePassword(email, newPassword);
            return ResponseEntity.ok("Password updated successfully");
        }
        return ResponseEntity.badRequest().body("Invalid information in JSON object.");

    }


}
