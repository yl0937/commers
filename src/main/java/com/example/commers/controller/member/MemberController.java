package com.example.commers.controller.member;

import com.example.commers.domain.member.Member;
import com.example.commers.service.member.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    void register(@RequestBody Member request){
        memberService.createMember(request);
    }

    @PostMapping("/logIn")
    void logIn(@RequestBody Member request){
        String email = request.getEmail();
        String password = request.getPassword();
        String result = memberService.logInMember(email,password);
        System.out.println(result);
    }



}
