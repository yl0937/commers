package com.example.commers.service;
import com.example.commers.domain.Auth;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.commers.domain.Member;
import com.example.commers.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    public Member createMember(Auth.SignUp request){
        boolean exists = this.memberRepository.existsByEmail(request.getEmail());
        if(exists){
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        return memberRepository.save(Member.builder()
                        .nickname(request.getNickname())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .phone(request.getPhone())
                        .birth(request.getBirth())
                        .roles(request.getRoles())
                        .build());
    }

    public Member checkMember(String email,String password){
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isEmpty())
        {
            throw new RuntimeException("해당하는 유저를 찾을 수 없습니다.");
        }
        if (!this.passwordEncoder.matches(password,member.get().getPassword())){
            throw new RuntimeException("pwd - 해당하는 유저를 찾을 수 없습니다.");
        }
        else {
            return member.get();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("couldn't find user -> " + email));
    }
}
