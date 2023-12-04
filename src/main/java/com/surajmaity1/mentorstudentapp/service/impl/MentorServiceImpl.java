package com.surajmaity1.mentorstudentapp.service.impl;

import com.surajmaity1.mentorstudentapp.dto.LoginDto;
import com.surajmaity1.mentorstudentapp.dto.RegisterDto;
import com.surajmaity1.mentorstudentapp.entity.Mentor;
import com.surajmaity1.mentorstudentapp.exception.MentorStudentException;
import com.surajmaity1.mentorstudentapp.repository.MentorRepository;
import com.surajmaity1.mentorstudentapp.security.JwtTokenProvider;
import com.surajmaity1.mentorstudentapp.service.MentorService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MentorServiceImpl implements MentorService {

    private AuthenticationManager authenticationManager;
    private MentorRepository mentorRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;


    public MentorServiceImpl(AuthenticationManager authenticationManager,
                             MentorRepository mentorRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.mentorRepository = mentorRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {

        // add check for username exists in database
        if(mentorRepository.existsByUsername(registerDto.getUsername())){
            throw new MentorStudentException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
        }

        // add check for email exists in database
        if(mentorRepository.existsByEmail(registerDto.getEmail())){
            throw new MentorStudentException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        Mentor mentor = new Mentor();
        mentor.setName(registerDto.getName());
        mentor.setUsername(registerDto.getUsername());
        mentor.setEmail(registerDto.getEmail());
        mentor.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        mentorRepository.save(mentor);

        return "Mentor registered successfully!.";
    }
}
