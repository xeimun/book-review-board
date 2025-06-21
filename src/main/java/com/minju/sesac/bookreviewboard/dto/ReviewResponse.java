package com.minju.sesac.bookreviewboard.dto;

import com.minju.sesac.bookreviewboard.domain.Review;
import lombok.Getter;

@Getter
public class ReviewResponse {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String bookTitle;
    private String bookAuthor;
    private int rating;

    public static ReviewResponse from(Review review) {
        ReviewResponse response = new ReviewResponse();
        response.id = review.getId();
        response.title = review.getTitle();
        response.content = review.getContent();
        response.author = review.getAuthor();
        response.bookTitle = review.getBookTitle();
        response.bookAuthor = review.getBookAuthor();
        response.rating = review.getRating();
        return response;
    }
}
