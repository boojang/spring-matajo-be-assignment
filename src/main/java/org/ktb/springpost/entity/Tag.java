package org.ktb.springpost.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tag")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag_name", length = 20, nullable = false, unique = true)
    private String tagName;

    // PostTag와 1:N 관계
    @OneToMany(mappedBy = "tag")
    private List<PostTag> postTags = new ArrayList<>();

    @Builder
    public Tag(String tagName) {
        this.tagName = tagName;
    }
}