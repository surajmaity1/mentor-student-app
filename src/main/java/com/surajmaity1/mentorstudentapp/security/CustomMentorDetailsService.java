package com.surajmaity1.mentorstudentapp.security;

import com.surajmaity1.mentorstudentapp.entity.Mentor;
import com.surajmaity1.mentorstudentapp.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Qualifier("customMentorDetailsService")
public class CustomMentorDetailsService implements UserDetailsService {
    private MentorRepository mentorRepository;

    public CustomMentorDetailsService(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Mentor mentor = mentorRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Mentor not found with username or email: " + usernameOrEmail));

        return new org.springframework.security.core
                .userdetails.User(mentor.getEmail(),
                mentor.getPassword(),
                new ArrayList<>()
        );
    }
}
