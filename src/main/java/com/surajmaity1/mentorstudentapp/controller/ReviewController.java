package com.surajmaity1.mentorstudentapp.controller;

import com.surajmaity1.mentorstudentapp.model.request.ReviewRequest;
import com.surajmaity1.mentorstudentapp.service.ReviewService;
import com.surajmaity1.mentorstudentapp.utils.ExtractJWT;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController (ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/post")
    public ResponseEntity<Object> postReview(@RequestHeader(value="Authorization") String token,
                                             @RequestBody ReviewRequest reviewRequest) throws Exception {
        String email = ExtractJWT.payloadJWTExtraction(token, "{\"sub\"");

        if (email == null) {
            throw new Exception("User Email Not Found");
        }

        String response = reviewService.postReview(email, reviewRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @GetMapping
//    public
}
