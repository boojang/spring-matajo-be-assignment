package org.ktb.springpost.repository;

import jakarta.persistence.EntityManager;
import org.ktb.springpost.dto.MemberRequestDto;
import org.ktb.springpost.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberJpaRepository {

    private final EntityManager em;

    @Autowired
    public MemberJpaRepository(EntityManager em) {
        this.em = em;
    }

    // 멤버 생성
    public void createMember(Member member) {
        em.persist(member);
    }

    // 멤버 수정
    public void updateMember(Long id, MemberRequestDto memberRequestDto) {
        Member findMember = em.find(Member.class, id);

        // 멤버가 있다면 업데이트
        if(findMember != null) {
            findMember.updateMember(memberRequestDto);
        }
    }

    // 멤버 삭제
    public void deleteMember(Long id) {
        Member findMember = em.find(Member.class, id);

        // 멤버가 있다면 삭제
        if(findMember != null) {
            em.remove(findMember);
        }
    }

    // 로그인
    public Member findByEmailAndPassword(String email, String password) {
        return em.createQuery("select m from Member m where m.email = :email and m.password = :password", Member.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
    }

    // ID를 통한 단건 조회
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    // 이메일을 통한 단건 조회
    public Member findByEmail(String email) {
        return em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    // 유저네임을 통한 단건 조회
    public Member findByUsername(String username) {
        return em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    // 일정 나이 범위에 있는 멤버 다건 조회
    public List<Member> findBetweenAge(int minAge, int maxAge) {
        return em.createQuery("select m from Member m where m.age >= :minAge and m.age <= :maxAge", Member.class)
                .setParameter("minAge", minAge)
                .setParameter("maxAge", maxAge)
                .getResultList();
    }

    // 멤버 다건 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

}
