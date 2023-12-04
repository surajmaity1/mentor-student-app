package com.surajmaity1.mentorstudentapp.model.request;

import lombok.Data;


@Data
public class RecommendStudentRequest {
    private String username;
    private String recommendationText;
}
