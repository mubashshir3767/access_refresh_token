package com.example.refresh_access_token.controller;

import com.example.refresh_access_token.entity.UserEntity;
import com.example.refresh_access_token.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN') and hasAuthority('ADD')")
    @GetMapping("/list")
    public List<UserEntity> getUserList(){
        return userRepository.findAll();
    }

    @PreAuthorize("hasRole('MODERATOR') and hasAuthority('LIST')")
    @GetMapping("/list1")
    public List<UserEntity> getUserList1(){
        return userRepository.findAll();
    }




}
