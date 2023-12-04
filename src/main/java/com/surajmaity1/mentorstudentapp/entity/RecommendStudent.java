package com.surajmaity1.mentorstudentapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "recommend_student")
@Data
public class RecommendStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "recommendation_text", columnDefinition = "TEXT")
    private String recommendationText;

    @Column(name = "shareable_link")
    private String shareableLink;

}
