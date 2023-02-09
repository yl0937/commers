package com.example.commers.service.member;

import com.example.commers.domain.member.Member;
import com.example.commers.repository.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void createMember(Member request){
        Member newMember = new Member();
        newMember.setNickname(request.getNickname());
        newMember.setEmail(request.getEmail());
        newMember.setPhone(request.getPhone());
        newMember.setPassword(request.getPassword());
        newMember.setBirth(request.getBirth());
        memberRepository.save(newMember);
    }

    public String logInMember(String email,String password){
        Member member = memberRepository.getMemberByEmail(email);
        if(member==null)
        {
            return "Fail";
        }
        String memPwd = member.getPassword();
        if(memPwd.equals(password)){
            return "SUCCESS";
        }else {
            return "WRONG";
        }
    }
}
