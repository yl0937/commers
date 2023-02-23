package com.example.commers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/registerPage")
    public String registerPage(){
        return "member/registerPage";
    }

    @RequestMapping("/testPage")
    public String testPage(){
        return "test";
    }

    @RequestMapping("/loginPage")
    public String logInPage() {return "member/logInPage";}

    @RequestMapping("/cartPage")
    public String cartPage() {return "cart";}
}
