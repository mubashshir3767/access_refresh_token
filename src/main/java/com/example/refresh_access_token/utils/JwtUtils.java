package com.example.refresh_access_token.utils;

import com.example.refresh_access_token.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtUtils {
    static String jwtAccessSecretKey = "bsldsfsdfefbiubefisanecninaigjaebaic";
    static String jwtRefreshSecretKey = "dddddddddddddddddddddddddddddddddd";
    static long expirationAccessTime = 120000;
    static long expirationRefreshTime = 1_000 * 60 * 60 * 24;

    public static synchronized String generateAccessToken(
            UserEntity userEntity
    ) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, jwtAccessSecretKey)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expirationAccessTime))
                .setSubject(userEntity.getUsername())
                .claim("authorities", getAuthorities(userEntity))
                .compact();
    }

    public static synchronized String generateRefreshToken(
            UserEntity userEntity
    ) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, jwtRefreshSecretKey)
                .setSubject(userEntity.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expirationRefreshTime))
                .compact();
    }

    public static synchronized Claims isValidRefreshToken(String token){
        return getRefreshClaim(token);
    }
    public static synchronized Claims isValidAccessToken(String token){
        return getAccessClaim(token);
    }
    private static synchronized Claims getAccessClaim(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtAccessSecretKey).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }
    private static synchronized Claims getRefreshClaim(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtRefreshSecretKey).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }
    private static List<String> getAuthorities(UserEntity userEntity) {
        List<String> roles = new ArrayList<>();

        userEntity.getPermissionEnumList().forEach((permissionEnum) -> {
            roles.add(permissionEnum.name());
        });

        userEntity.getPerRoleEnumList().forEach((user) -> {
            roles.add("ROLE_" + user);
        });

        return roles;
    }
    public static List<String> getAuthorities(Claims claims){
        return (List<String>)claims.get("authorities");
    }
}
