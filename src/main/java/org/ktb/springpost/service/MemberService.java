package org.ktb.springpost.service;

import org.ktb.springpost.dto.MemberRequestDto;
import org.ktb.springpost.entity.Member;
import org.ktb.springpost.repository.MemberJpaRepository;
import org.ktb.springpost.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberJpaRepository memberJpaRepository,
                         BCryptPasswordEncoder bCryptPasswordEncoder,
                         MemberRepository memberRepository) {
        this.memberJpaRepository = memberJpaRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.memberRepository = memberRepository;
    }

    // 멤버 생성
    @Transactional
    public void createMember(MemberRequestDto memberRequestDto) {
        memberRequestDto.setPassword(bCryptPasswordEncoder.encode(memberRequestDto.getPassword()));
        memberJpaRepository.createMember(memberRequestDto.toEntity());

        // MemberRepository 사용
//        memberRepository.save(memberRequestDto.toEntity());
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

        // 수정은 변경감지(Dirty Checking)을 이용하는 방식이 좋습니다.
        findMember.updateMember(memberRequestDto);
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

        // MemberRepository 사용
//         memberRepository.deleteById(id);
    }

    // 로그인
    public Member findByEmailAndPassword(String email, String password) {
        return memberJpaRepository.findByEmailAndPassword(email, bCryptPasswordEncoder.encode(password));
        // MemberRepository 사용
//         return memberRepository.findByEmailAndPassword(email, bCryptPasswordEncoder.encode(password));
    }


    // ID를 통한 단건 조회
    public Member findById(Long id) {
        return memberJpaRepository.findById(id);
        // MemberRepository 사용
        // 기본적으로 제공해주는 findById는 Optional 형태로 반환해줘서 Member 형태로 변환 시키는 과정입니다.
        // orElseThrow() 안쪽 부분은 람다식입니다.
//        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("회원이 없습니다."));
    }

    // 이메일을 통한 단건 조회
    public Member findByEmail(String email) {
        return memberJpaRepository.findByEmail(email);
        // MemberRepository 사용
//        return memberRepository.findByEmail(email);
    }

    // 유저네임을 통한 단건 조회
    public Member findByUsername(String username) {
        return memberJpaRepository.findByUsername(username);
        // MemberRepository 사용
//        return memberRepository.findByUsername(username);
    }

    // 일정 나이 범위에 있는 멤버 다건 조회
    public List<Member> findBetweenAge(int minAge, int maxAge) {
        return memberJpaRepository.findBetweenAge(minAge, maxAge);
        // MemberRepository 사용
//        return memberRepository.findByAgeBetween(minAge, maxAge);
    }

    // 멤버 다건 조회
    public List<Member> findAll() {
        return memberJpaRepository.findAll();
        // MemberRepository 사용
//        return memberRepository.findAll();
    }
}
