package org.ktb.springpost.service;

import org.ktb.springpost.dto.MemberRequestDto;
import org.ktb.springpost.entity.Member;
import org.ktb.springpost.repository.MemberJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberService(MemberJpaRepository memberJpaRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberJpaRepository = memberJpaRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // 멤버 생성
    @Transactional
    public void createMember(MemberRequestDto memberRequestDto) {
        memberRequestDto.setPassword(bCryptPasswordEncoder.encode(memberRequestDto.getPassword()));
        memberJpaRepository.createMember(memberRequestDto.toEntity());
    }

    // 멤버 수정
    @Transactional
    public void updateMember(Long id, MemberRequestDto memberRequestDto) {
        if(id == null) {
            throw new IllegalArgumentException("ID가 필요합니다.");
        }

        // 멤버가 있는지 확인
        Member findMember = memberJpaRepository.findById(id);
        if(findMember == null) {
            throw new IllegalArgumentException("회원이 없습니다.");
        }

        memberJpaRepository.updateMember(id, memberRequestDto);
    }

    // 멤버 삭제
    @Transactional
    public void deleteMember(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("ID가 필요합니다.");
        }

        // 멤버가 있는지 확인
        Member findMember = memberJpaRepository.findById(id);
        if(findMember == null) {
            throw new IllegalArgumentException("회원이 없습니다.");
        }

        memberJpaRepository.deleteMember(id);
    }

    // 로그인
    public Member findByEmailAndPassword(String email, String password) {
        return memberJpaRepository.findByEmailAndPassword(email, bCryptPasswordEncoder.encode(password));
    }


    // ID를 통한 단건 조회
    public Member findById(Long id) {
        return memberJpaRepository.findById(id);
    }

    // 이메일을 통한 단건 조회
    public Member findByEmail(String email) {
        return memberJpaRepository.findByEmail(email);
    }

    // 유저네임을 통한 단건 조회
    public Member findByUsername(String username) {
        return memberJpaRepository.findByUsername(username);
    }

    // 일정 나이 범위에 있는 멤버 다건 조회
    public List<Member> findBetweenAge(int minAge, int maxAge) {
        return memberJpaRepository.findBetweenAge(minAge, maxAge);
    }

    // 멤버 다건 조회
    public List<Member> findAll() {
        return memberJpaRepository.findAll();
    }
}
