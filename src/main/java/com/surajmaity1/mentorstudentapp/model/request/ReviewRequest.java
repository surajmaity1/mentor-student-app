package com.surajmaity1.mentorstudentapp.model.request;

import lombok.Data;

import java.util.Optional;

@Data
public class ReviewRequest {
    private String username;
    private double rating;
    private Optional<String> description;
}
