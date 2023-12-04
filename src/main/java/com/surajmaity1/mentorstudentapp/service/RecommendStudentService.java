package com.surajmaity1.mentorstudentapp.service;

import com.surajmaity1.mentorstudentapp.model.request.RecommendStudentRequest;
import org.springframework.web.multipart.MultipartFile;

public interface RecommendStudentService {
    String recommendStudent(String email, RecommendStudentRequest recommendStudentRequest, MultipartFile file) throws Exception;
}
