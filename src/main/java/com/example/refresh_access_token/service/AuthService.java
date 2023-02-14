package com.example.refresh_access_token.service;

import com.example.refresh_access_token.dto.response.ApiResponse;
import com.example.refresh_access_token.entity.UserEntity;
import com.example.refresh_access_token.receive.UserRegisterDto;
import com.example.refresh_access_token.receive.UsernamePasswordRequestDto;
import com.example.refresh_access_token.repository.UserRepository;
import com.example.refresh_access_token.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity register(UserRegisterDto userRegisterDto) {
        UserEntity userEntity = new UserEntity(
                userRegisterDto.getName(),
                userRegisterDto.getUsername(),
                passwordEncoder.encode(userRegisterDto.getPassword()),
                userRegisterDto.getPerRoleEnumList(),
                userRegisterDto.getPermissionEnumList()
        );
        return userRepository.save(userEntity);
    }

    public ApiResponse login(UsernamePasswordRequestDto usernamePasswordRequestDto) {
        UserEntity userEntity = userRepository.findByUsername(usernamePasswordRequestDto.getUsername());
        if (userEntity == null) {
            throw new UsernameNotFoundException("username pr password not found");
        }
        if (!passwordEncoder.matches(usernamePasswordRequestDto.getPassword(), userEntity.getPassword())) {
            throw new UsernameNotFoundException("username or password not found");
        }
        return new ApiResponse(
                0,
                "SUCCESS",
                Map.of(
                        "access_token", JwtUtils.generateAccessToken(userEntity),
                        "refresh_token", JwtUtils.generateRefreshToken(userEntity)
                )
        );
    }

    public ApiResponse getAccessToken(String refreshToken) {
        Claims cl = JwtUtils.isValidRefreshToken(refreshToken);
        if (cl != null) {
            String username = cl.getSubject();
            UserEntity byUsername = userRepository.findByUsername(username);
            if (byUsername != null) {
                return new ApiResponse(
                        0,
                        "Success",
                        Map.of(
                                "access_token", JwtUtils.generateAccessToken(byUsername)
                        )
                );
            }
        }
        return null;
    }
}
