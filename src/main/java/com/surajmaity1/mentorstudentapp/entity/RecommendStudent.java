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

    @Column(name = "date")
    private String date;

    @Column(name = "mentor_id")
    private Long mentorId;

    @Column(name = "user_id")
    private Long userId;
}
