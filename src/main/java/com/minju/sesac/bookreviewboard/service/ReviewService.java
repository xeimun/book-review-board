package com.minju.sesac.bookreviewboard.service;

import com.minju.sesac.bookreviewboard.domain.Review;
import com.minju.sesac.bookreviewboard.dto.ReviewCreateRequest;
import com.minju.sesac.bookreviewboard.dto.ReviewPageResponse;
import com.minju.sesac.bookreviewboard.dto.ReviewResponse;
import com.minju.sesac.bookreviewboard.dto.ReviewSearchRequest;
import com.minju.sesac.bookreviewboard.dto.ReviewUpdateRequest;
import com.minju.sesac.bookreviewboard.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public List<ReviewResponse> getAll() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                      .map(review -> ReviewResponse.from(review))
                      .collect(Collectors.toList());
    }

    public ReviewResponse update(Long id, ReviewUpdateRequest request) {
        Review review = reviewRepository.findById(id)
                                        .orElseThrow(() -> new EntityNotFoundException("해당 번호의 리뷰를 찾을 수 없습니다."));

        review.setTitle(request.getTitle());
        review.setContent(request.getContent());
        review.setAuthor(request.getAuthor());
        review.setBookTitle(request.getBookTitle());
        review.setBookAuthor(request.getBookAuthor());
        review.setRating(request.getRating());

        return ReviewResponse.from(review);
    }

    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ReviewPageResponse search(ReviewSearchRequest request) {

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        Page<ReviewResponse> reviews = reviewRepository.findByBookTitleContaining(request.getKeyword(), pageable)
                                                       .map(review -> ReviewResponse.from(review));

        return ReviewPageResponse.from(reviews, request);
    }
}
