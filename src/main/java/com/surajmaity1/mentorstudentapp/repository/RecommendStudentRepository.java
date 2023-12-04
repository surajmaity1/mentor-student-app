package com.surajmaity1.mentorstudentapp.repository;

import com.surajmaity1.mentorstudentapp.entity.RecommendStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendStudentRepository extends JpaRepository<RecommendStudent, Long> {
    RecommendStudent findByEmailAndUsername(String email, String username);
    List<RecommendStudent> findByUsername(String username);
    List<RecommendStudent> findByEmail(String email);
}
