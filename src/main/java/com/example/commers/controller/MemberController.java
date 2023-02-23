package com.example.commers.controller;

import com.example.commers.config.secret.BaseResponse;
import com.example.commers.config.security.JwtAuthenticationFilter;
import com.example.commers.config.security.TokenProvider;
import com.example.commers.domain.Member;
import com.example.commers.domain.Auth;
import com.example.commers.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.commers.config.secret.BaseResponseStatus.SUCCESS;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    private final TokenProvider tokenProvider;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @PostMapping("/register")
    public BaseResponse<?> register(@RequestBody Auth.SignUp request){
        return new BaseResponse<>(memberService.createMember(request));
    }

    @PostMapping("/logIn")
    public BaseResponse<?> logIn(@RequestBody Member request,HttpServletRequest request2){
        String email = request.getEmail();
        String password = request.getPassword();
        Member result = memberService.checkMember(email,password);

        String jwt = tokenProvider.generateToken(result.getEmail(),request.getRoles());

        HttpSession session = request2.getSession();
        int sTime = 60*60;
        session.setMaxInactiveInterval(sTime);
        session.setAttribute("JWT",jwt);

        return new BaseResponse<>(SUCCESS);

    }

    @PostMapping("test")
    public String getmemberId(HttpServletRequest request){

        HttpSession session = request.getSession();
        String jwt = (String) session.getAttribute("JWT");
        try{
            Authentication a = tokenProvider.getAuthentication(jwt);
            return jwt;
        }catch (RuntimeException e){
            return null;
        }
    }

    @PostMapping("logout")
    public BaseResponse<?> logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return new BaseResponse<>(SUCCESS);
    }




}
