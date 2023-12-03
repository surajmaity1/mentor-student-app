package com.surajmaity1.mentorstudentapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class MentorStudentException extends RuntimeException {

    @Getter
    private HttpStatus status;
    private String message;

    public MentorStudentException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public MentorStudentException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
