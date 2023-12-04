package com.surajmaity1.mentorstudentapp.service.impl;

import com.surajmaity1.mentorstudentapp.entity.Review;
import com.surajmaity1.mentorstudentapp.model.request.ReviewRequest;
import com.surajmaity1.mentorstudentapp.repository.ReviewRepository;
import com.surajmaity1.mentorstudentapp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public String postReview(String email, ReviewRequest reviewRequest) throws Exception {
        Review validateReview = reviewRepository.findByEmailAndUsername(email, reviewRequest.getUsername());

        if (validateReview != null) {
            throw new Exception("Review Already Given");
        }

        if (reviewRequest.getRating() < 1 || reviewRequest.getRating() > 5){
            throw new Exception("Invalid Rating. Please Choose Rating Between 1 to 5");
        }

        Review review = new Review();
        review.setEmail(email);
        review.setUsername(reviewRequest.getUsername());
        review.setRating(reviewRequest.getRating());

        if (reviewRequest.getDescription().isPresent()) {
            review.setDescription(
                    reviewRequest.getDescription()
                            .map(Object::toString)
                            .orElse(null)
            );
        }

        reviewRepository.save(review);
        return "Review Posted successfully!.";
    }

    @Override
    public Boolean studentReviewListed(String email, String username) {
        Review validateReview = reviewRepository.findByEmailAndUsername(email, username);
        return validateReview != null;
    }
}