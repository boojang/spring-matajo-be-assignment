package org.ktb.springpost.controller;

import lombok.RequiredArgsConstructor;
import org.ktb.springpost.dto.ApiResponse;
import org.ktb.springpost.dto.PostResponseDto;
import org.ktb.springpost.entity.Post;
import org.ktb.springpost.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController //Controller + ResponseBody 추가 -> json 형태로 객체 데이터를 반환
@RequestMapping("api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostResponseDto>>> getPosts(){
        // Service에서 DTO 리스트 받기
        List<PostResponseDto> posts = postService.getAllPosts();

        //API 응답 형식으로 반환
        ApiResponse<List<PostResponseDto>> response = ApiResponse.<List<PostResponseDto>>builder()
                .success(true)
                .message("게시글 목록 조회 성공")
                .data(posts)
                .build();
        //ResponseEntity<ApiResponse<T>> 형태로 반환
        //ResponseEntity.ok(response)를 사용해 http 200 명시적으로 설정
        //Service에서 받은 DTO 리스트를 ApiResponse로 감싸서 클라이언트에게 반환
        return ResponseEntity.ok(response);
    }

//    @PostMapping
//    public ResponseEntity<Long> createPost(@RequestBody PostCreateRequest request) {
//        Long postId = postService.createPost(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(postId);
//    }

}
