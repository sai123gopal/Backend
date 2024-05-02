package com.saigopa.travel.Travel.Helpers;

import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;
import io.jsonwebtoken.*;

public class JwtUtil {

    private static SecretKey key = Jwts.SIG.HS256.key().build();
    private static final long EXPIRATION_TIME = 1296000000; //15 days

    public static String generateToken(String userId) {
        return Jwts.builder()
                .subject(userId)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .id(UUID.randomUUID().toString())
                .compact();
    }

    public static String extractUserId(String token) throws JwtException {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (Exception e) {
            throw new JwtException("Token expires Please login again");
        }

    }
}