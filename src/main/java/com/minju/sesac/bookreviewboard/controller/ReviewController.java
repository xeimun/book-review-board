package com.minju.sesac.bookreviewboard.controller;

import com.minju.sesac.bookreviewboard.dto.ReviewCreateRequest;
import com.minju.sesac.bookreviewboard.dto.ReviewResponse;
import com.minju.sesac.bookreviewboard.dto.ReviewUpdateRequest;
import com.minju.sesac.bookreviewboard.service.ReviewService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponse> create(@RequestBody ReviewCreateRequest request) {
        ReviewResponse response = reviewService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAll() {
        List<ReviewResponse> responses = reviewService.getAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> update(@PathVariable Long id,
                                                 @RequestBody ReviewUpdateRequest request) {
        ReviewResponse response = reviewService.update(id, request);
        return ResponseEntity.ok(response);
    }

}
