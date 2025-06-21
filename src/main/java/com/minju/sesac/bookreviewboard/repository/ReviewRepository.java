package com.minju.sesac.bookreviewboard.repository;

import com.minju.sesac.bookreviewboard.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
