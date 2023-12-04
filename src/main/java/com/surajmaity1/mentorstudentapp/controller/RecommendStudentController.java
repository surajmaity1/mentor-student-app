package com.surajmaity1.mentorstudentapp.controller;

import com.surajmaity1.mentorstudentapp.model.request.RecommendStudentRequest;
import com.surajmaity1.mentorstudentapp.service.impl.RecommendStudentServiceImpl;
import com.surajmaity1.mentorstudentapp.utils.ExtractJWT;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/recommend/student")
public class RecommendStudentController {
    private RecommendStudentServiceImpl recommendStudentService;

    public RecommendStudentController(RecommendStudentServiceImpl recommendStudentService) {
        this.recommendStudentService = recommendStudentService;
    }

    @PostMapping
    public ResponseEntity<Object> recommendStudent(@RequestHeader(value = "Authorization") String token,
                                                   @RequestParam(value = "file") MultipartFile file,
                                                   @RequestParam(value = "username") String username,
                                                   @RequestParam(value = "recommendationText") String recommendationText) throws Exception {
        String email = ExtractJWT.payloadJWTExtraction(token, "{\"sub\"");

        if (email == null) {
            throw new Exception("User Email Not Found");
        }

        RecommendStudentRequest recommendStudentRequest = new RecommendStudentRequest();
        recommendStudentRequest.setUsername(username);
        recommendStudentRequest.setRecommendationText(recommendationText);

        String response = recommendStudentService.recommendStudent(email, recommendStudentRequest, file);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
