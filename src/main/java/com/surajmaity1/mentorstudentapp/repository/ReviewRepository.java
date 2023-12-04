package com.surajmaity1.mentorstudentapp.repository;

import com.surajmaity1.mentorstudentapp.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByEmailAndUsername(String email, String username);
    @Modifying
    void deleteByUsername(@Param("username") String username);
    List<Review> findByUsername(@RequestParam("username") String username);
    List<Review> findByRating(@RequestParam("rating") double rating);
}
