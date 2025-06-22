package com.minju.sesac.bookreviewboard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewSearchRequest {
    private String author;                  // 리뷰 작성자 (정확 일치)
    private String bookTitle;               // 책 제목 (정확 일치)
    private String bookTitleContains;       // 책 제목 키워드 포함 검색
    private String bookAuthor;              // 책 저자 (정확 일치)
    private String titleContains;           // 리뷰 제목 키워드 포함 검색
    private String contentContains;         // 리뷰 본문 키워드 포함 검색
    private Integer rating;                 // 평점 (1~5) (정확 일치)
    private Integer minRating;              // 최소 평점
    private Integer maxRating;              // 최대 평점
    private String sort;                    // 정렬 기준 (createdAt,desc)
    private int page = 0;                   // 몇 번째 페이지인지 (0부터 시작)
    private int size = 10;                  // 한 페이지에 가져올 항목 수
}
