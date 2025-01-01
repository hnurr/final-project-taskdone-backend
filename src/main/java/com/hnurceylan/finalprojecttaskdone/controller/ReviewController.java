package com.hnurceylan.finalprojecttaskdone.controller;

import com.hnurceylan.finalprojecttaskdone.entities.Review;
import com.hnurceylan.finalprojecttaskdone.services.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{serviceProviderId}/{customerId}")
    public Review addReview(@PathVariable Long serviceProviderId, @PathVariable Long customerId, @RequestBody Review review) {
        return reviewService.addReview(serviceProviderId, customerId, review);
    }

    @GetMapping("/{serviceProviderId}")
    public List<Review> getReviewsByServiceProvider(@PathVariable Long serviceProviderId) {
        return reviewService.getReviewsByServiceProvider(serviceProviderId);
    }
}
