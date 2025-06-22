package com.minju.sesac.bookreviewboard.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder
public class ReviewPageResponse {
    private int page;
    private int size;
    private long totalCount;
    private int totalPages;
    private List<ReviewResponse> reviews;

    public static ReviewPageResponse from(Page<ReviewResponse> reviews, ReviewSearchRequest request) {
        return ReviewPageResponse.builder()
                                 .page(request.getPage())
                                 .size(request.getSize())
                                 .totalCount(reviews.getTotalElements())
                                 .totalPages(reviews.getTotalPages())
                                 .reviews(reviews.getContent())
                                 .build();
    }
}
