package org.ktb.springpost.entity;
// id, title, content, createdAt, updatedAt
//제목,내용 생성/수정 날짜

import jakarta.persistence.*;
import lombok.*;
import org.ktb.springpost.entity.common.BaseEntity;

@Entity
@Table(name = "posts")
@Getter
// 아무것도 없는 생성자 자동 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)

// 모든 필드가 있는 생성자 자동 생성
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Post extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(length = 30, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    public static Post create(String title, String content){
        return Post.builder()
        .title(title)
        .content(content)
        .build();
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    //게시판
    //게시글- 태그 관계 (양방향)
    //무슨 뜻?
//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<PostTag> postTags = new ArrayList<>();
}
