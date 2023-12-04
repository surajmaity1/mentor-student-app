package com.surajmaity1.mentorstudentapp.repository;

import com.surajmaity1.mentorstudentapp.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MentorRepository  extends JpaRepository<Mentor, Long> {
    Optional<Mentor> findByEmail(String email);
    Optional<Mentor> findByUsernameOrEmail(String username, String email);
    Optional<Mentor> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
