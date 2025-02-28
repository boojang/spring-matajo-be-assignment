package org.ktb.springpost.entity;

import jakarta.persistence.*;
import lombok.*;
import org.ktb.springpost.dto.MemberRequestDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE) // Builder와 함께 사용하기 위해 private으로 설정
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String username;

    private int age;

    // 멤버 업데이트 메소드
    public void updateMember(MemberRequestDto memberRequestDto) {
        if (memberRequestDto.getUsername() != null) this.username = memberRequestDto.getUsername();
        if (memberRequestDto.getEmail() != null) this.email = memberRequestDto.getEmail();
        if (memberRequestDto.getPassword() != null) this.password = memberRequestDto.getPassword();
    }
}
