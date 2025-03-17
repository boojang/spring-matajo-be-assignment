package org.ktb.springpost.dto;
//{
//success: true / false,
//message: "로그인을 성공했습니다.",
//data: {
//post_id: 1,
//post_title: "제목입니다."
//post_content: "내용입니다."
//        }
//        }

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ktb.springpost.entity.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//DTO
//계층 간 데이터 전송을 위한 객체
//클라이언트 - 서버 간 데이터 교환
//엔티티 객체를 노출하지 않고 필요한 데이터만 전송
//엔티티 객체를 클라이언트에 적합한 형태로 변환 + 불필요한 정보 제외, 필요한 정보만 포함
//계층 분리 - db entity와 클라이언트에 노출되는 데이터 분리

@Getter //모든 필드 getter 자동 생성
@NoArgsConstructor //파라미터 없는 기본 생성자 자동 생성 public PostResponseDto() {}
@AllArgsConstructor //모든 필드를 파라미터로 받는 생성자 자동 생성 public PostResponseDto(Long id, String title, String content, ...)
@Builder // 빌더 패턴을 구현한 내부 클래스,메소드 자동 생성 PostResponseDto.builder().id(1L).title("제목").content("내용").build()
public class PostResponseDto {
    //게시글 리스트를 DB에서 가져오기
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    // 단일 Post 엔티티를 DTO로 변환하는 정적 메소드
    // of 메서드 : 단일 entity를 dto로 변환
    public static PostResponseDto of(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .build();
    }

    // Post 엔티티 리스트를 DTO 리스트로 변환하는 정적 메소드
    // listOf 메서드 : 여러 entity를 dto로 변환
    public static List<PostResponseDto> listOf(List<Post> posts) {
        //스트림 생성 List<Post>를 스트림으로 변환
        //스트림 : 데이터 소스를 추상화하여 함수형 프로그래밍 처리리
        return posts.stream()
                //map : 스트림의 요소를 다른 형태로 변환
                //PostResponseDto::of : Post 객체를 매개변수로 받아 PostResponseDto 객체로 변환
                //이 부분이 핵심입니다: 각 게시글 엔티티가 DTO로 변환되는 시점입니다.
                //PostResponseDto.of(post) 메서드가 각 Post 엔티티에 대해 호출
                .map(PostResponseDto::of)
                .collect(Collectors.toList());
    }

}
