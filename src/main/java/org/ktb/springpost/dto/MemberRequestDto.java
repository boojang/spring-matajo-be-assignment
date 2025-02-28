package org.ktb.springpost.dto;

import org.ktb.springpost.entity.Member;

public class MemberRequestDto {

    private String email;

    private String password;

    private String username;

    private int age;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .username(username)
                .age(age)
                .build();
    }
}
