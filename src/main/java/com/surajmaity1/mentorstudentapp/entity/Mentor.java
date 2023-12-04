package com.surajmaity1.mentorstudentapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mentor")
@Data
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "rating")
    private double rating;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
