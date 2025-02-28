package org.ktb.springpost.controller;

import org.ktb.springpost.dto.MemberRequestDto;
import org.ktb.springpost.entity.Member;
import org.ktb.springpost.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 멤버 생성
    @PostMapping
    public void createMember(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.createMember(memberRequestDto);
    }

    // 멤버 수정
    @PutMapping("/id/{id}")
    public void updateMember(@PathVariable Long id, @RequestBody MemberRequestDto memberRequestDto) {
        memberService.updateMember(id, memberRequestDto);
    }

    // 멤버 삭제
    @DeleteMapping("/id/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }

    // 로그인
    @GetMapping("/login")
    public void createMember(@RequestParam String email, @RequestParam String password) {
        memberService.findByEmailAndPassword(email, password);
    }

    // ID를 통한 단건 조회
    @GetMapping("/id/{id}")
    public Member findById(@PathVariable Long id) {
        return memberService.findById(id);
    }

    // 이메일을 통한 단건 조회
    @GetMapping("/email/{email}")
    public Member findByEmail(@PathVariable String email) {
        return memberService.findByEmail(email);
    }

    // 유저네임을 통한 단건 조회
    @GetMapping("/username/{username}")
    public Member findByUsername(@PathVariable String username) {
        return memberService.findByUsername(username);
    }

    // 일정 나이 범위에 있는 멤버 다건 조회
    @GetMapping("/age")
    public List<Member> findBetweenAge(@RequestParam("minAge") int minAge,
                                       @RequestParam("maxAge") int maxAge) {
        return memberService.findBetweenAge(minAge, maxAge);
    }

    // 멤버 다건 조회
    @GetMapping
    public List<Member> findAll() {
        return memberService.findAll();
    }
}
