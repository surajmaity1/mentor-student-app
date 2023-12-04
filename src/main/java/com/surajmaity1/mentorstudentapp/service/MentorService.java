package com.surajmaity1.mentorstudentapp.service;

import com.surajmaity1.mentorstudentapp.dto.LoginDto;
import com.surajmaity1.mentorstudentapp.dto.RegisterDto;

public interface MentorService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
