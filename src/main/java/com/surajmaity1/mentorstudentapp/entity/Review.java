package com.surajmaity1.mentorstudentapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "review")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "rating")
    private double rating;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
