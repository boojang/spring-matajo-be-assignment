package org.ktb.springpost.dto;

//요청데이터가 오는곳
//게시글 생성, 수정 필드 추가

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostReqeustDto {
    private String title;
    private String content;
}
