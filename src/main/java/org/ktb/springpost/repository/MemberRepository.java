package org.ktb.springpost.repository;

import org.ktb.springpost.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // Login
    Member findByEmailAndPassword(String email, String password);

    // Login을 JPQL로 작성도 가능합니다
    // JPQL은 객체 지향 쿼리라고 생각해주시면 됩니다.
    @Query("SELECT m FROM Member m WHERE m.email = :email AND m.password = :password")
    Member login(String email, String password);

    // 이메일을 통한 단건 조회
    Member findByEmail(String email);

    // 유저네임을 통한 단건 조회
    Member findByUsername(String username);

    // 일정 나이 범위에 있는 멤버 다건 조회
    List<Member> findByAgeBetween(int minAge, int maxAge);
}
