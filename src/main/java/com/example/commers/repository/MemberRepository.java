package com.example.commers.repository;

import com.example.commers.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

}
