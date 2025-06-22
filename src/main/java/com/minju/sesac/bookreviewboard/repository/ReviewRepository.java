package com.minju.sesac.bookreviewboard.repository;

import com.minju.sesac.bookreviewboard.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDSLRepository {
    Page<Review> findByBookTitleContaining(String bookTitle, Pageable pageable);

    Page<Review> findByAuthorAndRating(String author, Integer rating, Pageable pageable);

    Page<Review> findByRatingBetween(Integer minRating, Integer maxRating, Pageable pageable);
}
