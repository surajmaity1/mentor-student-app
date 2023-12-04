package com.surajmaity1.mentorstudentapp.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.surajmaity1.mentorstudentapp.entity.RecommendStudent;
import com.surajmaity1.mentorstudentapp.model.request.RecommendStudentRequest;
import com.surajmaity1.mentorstudentapp.repository.RecommendStudentRepository;
import com.surajmaity1.mentorstudentapp.service.RecommendStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class RecommendStudentServiceImpl implements RecommendStudentService {

    private RecommendStudentRepository recommendStudentRepository;

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    public RecommendStudentServiceImpl(RecommendStudentRepository recommendStudentRepository) {
        this.recommendStudentRepository = recommendStudentRepository;
    }

    @Override
    public String recommendStudent(String email, RecommendStudentRequest recommendStudentRequest, MultipartFile file) throws Exception {
        RecommendStudent validateRecommendStudent = recommendStudentRepository.findByEmailAndUsername(email, recommendStudentRequest.getUsername());

        if (validateRecommendStudent != null) {
            throw new Exception("LOI Already Provided");
        }

        RecommendStudent recommendStudent = new RecommendStudent();
        recommendStudent.setEmail(email);
        recommendStudent.setUsername(recommendStudentRequest.getUsername());
        recommendStudent.setRecommendationText(recommendStudentRequest.getRecommendationText());

        String uploadFileLink = uploadFile(file);
        recommendStudent.setShareableLink(uploadFileLink);

        recommendStudentRepository.save(recommendStudent);

        return "File uploaded. Check here : " + uploadFileLink;
    }

    private String uploadFile(MultipartFile file) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = "LOR" + "_" + file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        String baseUrl = "https://mentor-student-s3-bucket.s3.amazonaws.com/";
        return baseUrl + fileName;
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}
