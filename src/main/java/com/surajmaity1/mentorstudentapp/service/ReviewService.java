package com.surajmaity1.mentorstudentapp.service;

import com.surajmaity1.mentorstudentapp.model.request.ReviewRequest;

public interface ReviewService {
    String postReview(String email, ReviewRequest reviewRequest) throws Exception;
    Boolean studentReviewListed(String email, String username);
}
