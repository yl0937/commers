package com.example.commers.repository.member;

import com.example.commers.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
    Member getMemberByEmail(String email);
}
