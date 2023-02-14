package com.example.refresh_access_token.controller;

import com.example.refresh_access_token.dto.response.ApiResponse;
import com.example.refresh_access_token.entity.UserEntity;
import com.example.refresh_access_token.receive.UserRegisterDto;
import com.example.refresh_access_token.receive.UsernamePasswordRequestDto;
import com.example.refresh_access_token.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("register")
    public UserEntity register(@RequestBody UserRegisterDto userRegisterDto){
        return authService.register(userRegisterDto);
    }

    @PostMapping("login")
    public ApiResponse login(@RequestBody UsernamePasswordRequestDto usernamePasswordRequestDto){
        return authService.login(usernamePasswordRequestDto);
    }

    @PostMapping("refresh/token/blamlahaljlaslagascasckhascohaohscihqawfuhojaoihscoacos")
    public ApiResponse getAccessTokenByRefreshToken(
            @RequestBody Map<String,String> refreshToken
            ){
        return authService.getAccessToken(refreshToken.get("refresh_token"));
    }
}
