package com.minju.sesac.bookreviewboard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewSearchRequest {
    private String author;
    private String bookTitle;
    private String keyword;
    private Integer rating;
    private Integer minRating;
    private Integer maxRating;
    private int page = 0;
    private int size = 10;
}
