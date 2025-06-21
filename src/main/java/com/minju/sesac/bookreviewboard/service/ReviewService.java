package com.minju.sesac.bookreviewboard.service;

import com.minju.sesac.bookreviewboard.domain.Review;
import com.minju.sesac.bookreviewboard.dto.ReviewCreateRequest;
import com.minju.sesac.bookreviewboard.dto.ReviewResponse;
import com.minju.sesac.bookreviewboard.repository.ReviewRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewResponse create(ReviewCreateRequest request) {
        Review newReview = Review.from(request);
        return ReviewResponse.from(reviewRepository.save(newReview));
    }

    public List<ReviewResponse> getAll() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                      .map(review -> ReviewResponse.from(review))
                      .collect(Collectors.toList());
    }
}
