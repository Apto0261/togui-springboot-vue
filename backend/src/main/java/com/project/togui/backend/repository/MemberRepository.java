package com.project.togui.backend.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.togui.backend.entity.Member;

@Transactional
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    boolean existsByNickname(String Nickname);

    boolean existsByEmail(String email);

}
