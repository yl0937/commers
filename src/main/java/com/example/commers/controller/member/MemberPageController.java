package com.example.commers.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberPageController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/member/registerPage")
    public String registerPage(){
        return "member/registerPage";
    }
}
