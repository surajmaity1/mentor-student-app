package com.surajmaity1.mentorstudentapp.repository;

import com.surajmaity1.mentorstudentapp.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByUsername(@RequestParam("username") String username, Pageable pageable);
    Review findByEmailAndUsername(String email, String username);
    @Modifying
    void deleteByUsername(@Param("username") String username);
}
