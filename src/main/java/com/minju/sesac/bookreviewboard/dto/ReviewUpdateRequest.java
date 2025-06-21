package com.minju.sesac.bookreviewboard.dto;

import lombok.Getter;

@Getter
public class ReviewUpdateRequest {
    private String title;
    private String content;
    private String author;
    private String bookTitle;
    private String bookAuthor;
    private int rating;
}
