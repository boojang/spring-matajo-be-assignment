package org.ktb.springpost.repository;
//JPA를 사용해 구현

import org.ktb.springpost.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    //게시글 리스트 가져오기
    // 기본 findAll 메서드 -> JpaRepository에서 제공

    //생성일 기준 내림차순 정렬 게시글 조회 -> List 형태로 반환
    List<Post> findAllByOrderByCreatedAtDesc();

    List<Post> findAll();

    //페이징 처리
    //offset = 몇 번째 데이터부터 가져올지(시작점) / limit = 몇 개의 데이터를 가져올 지 (갯수)
}
