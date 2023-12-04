package com.surajmaity1.mentorstudentapp.controller;

import com.surajmaity1.mentorstudentapp.dto.LoginDto;
import com.surajmaity1.mentorstudentapp.dto.RegisterDto;
import com.surajmaity1.mentorstudentapp.response.JWTAuthResponse;
import com.surajmaity1.mentorstudentapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = userService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = userService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
