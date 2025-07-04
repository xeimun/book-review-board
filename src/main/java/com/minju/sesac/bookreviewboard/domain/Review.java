package com.minju.sesac.bookreviewboard.domain;

import com.minju.sesac.bookreviewboard.dto.ReviewCreateRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String author;
    private String bookTitle;
    private String bookAuthor;
    private int rating;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public static Review from(ReviewCreateRequest request) {
        Review newReview = new Review();
        newReview.title = request.getTitle();
        newReview.content = request.getContent();
        newReview.author = request.getAuthor();
        newReview.bookTitle = request.getBookTitle();
        newReview.bookAuthor = request.getBookAuthor();
        newReview.rating = request.getRating();
        return newReview;
    }
}
