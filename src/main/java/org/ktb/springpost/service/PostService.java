package org.ktb.springpost.service;

//게시글 리스트 조회
//게시글 상세조회
//게시글 등록
//게시글 수정

//Transactional - import 변경
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.ktb.springpost.dto.PostResponseDto;
import org.ktb.springpost.entity.Post;
import org.ktb.springpost.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //서비스 계층 컴포넌트
@RequiredArgsConstructor //field 필드에 대한 생성자 주입 자동으로 생성
@Transactional(readOnly = true)
public class PostService {

    //생성자 주입 방법으로 주입
    private final PostRepository postRepository;

    //모든 게시글 조회
    public List<PostResponseDto> getAllPosts() {

        //Repository에서 Entity로 데이터 조회
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        //entity -> dto 변환

//        List<PostResponseDto> dtoList = posts.stream()
//                .map(PostResponseDto::fromEntity)
//                .collect(Collectors.toList());
        return PostResponseDto.listOf(posts);
    }
}
