package com.minju.sesac.bookreviewboard.repository;

import com.minju.sesac.bookreviewboard.domain.Review;
import com.minju.sesac.bookreviewboard.dto.ReviewSearchRequest;
import org.springframework.data.domain.Page;

public interface ReviewQueryDSLRepository {
    // 책 제목 키워드 포함 + 평점 범위 + 최신순 정렬 검색
    Page<Review> searchByFilters(ReviewSearchRequest request);
}
