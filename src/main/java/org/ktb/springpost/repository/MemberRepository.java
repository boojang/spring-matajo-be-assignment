package org.ktb.springpost.repository;

import org.ktb.springpost.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // Login
    Member findByEmailAndPassword(String email, String password);

    // ID를 통한 단건 조회
    Member findById(long id);

    // 이메일을 통한 단건 조회
    Member findByEmail(String email);

    // 유저네임을 통한 단건 조회
    Member findByUsername(String username);

    // 일정 나이 범위에 있는 멤버 다건 조회
    List<Member> findByAgeBetween(int minAge, int maxAge);
}
