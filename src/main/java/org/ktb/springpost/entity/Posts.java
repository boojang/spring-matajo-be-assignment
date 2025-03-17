package org.ktb.springpost.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ktb.springpost.entity.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Posts extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "keeper_id", nullable = false)
    private Long keeperId;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "prefer_price", nullable = false)
    private Integer preferPrice;

    @Column(name = "hidden_status", nullable = false)
    private Boolean hiddenStatus = false;

    @Column(name = "discount_rate", nullable = false)
    private Float discountRate;

    // PostTag와 1:N 관계
    // post 하나에 여러 PostTag를 가질 수 있다.
    // mappedBy : 이 관계의 owner는 PostTag 엔티티의 post 필드
    // 양방향 관계에서는 한쪽만 외래 키를 관리해야 하는데, 여기서는 PostTag 엔티티가 그 역할을 담당합니다
    // cascade = CascadeType.ALL : Post 엔티티에 대한 모든 변경이 연관된 PostTag에도 적용
    // ex) Post를 삭제하면 그에 연결된 PostTag도 자동으로 삭제된다.
    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL, orphanRemoval = true)
    // 실제로 PostTag 객체를 담을 컬렉션을 초기화
    // Post와 Tag 사이의 연결 정보를 담는다.
    private List<PostTag> postTags = new ArrayList<>();

    // 연관관계 편의 메서드 -> 태그를 추가한다.
//    public void addTag(Tag tag) {
//        PostTag postTag = new PostTag(this, tag);
//        postTags.add(postTag);
//    }

    @Builder
    public Posts(Long keeperId, String title, String content, Integer preferPrice,
                Float discountRate, Boolean hiddenStatus) {
        this.keeperId = keeperId;
        this.title = title;
        this.content = content;
        this.preferPrice = preferPrice;
        this.discountRate = discountRate;
        this.hiddenStatus = hiddenStatus != null ? hiddenStatus : false;
    }
}

