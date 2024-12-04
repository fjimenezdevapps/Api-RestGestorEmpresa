package com.miempresa.apirestempresa.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

   
    private String JWT_SECRET = "my_secret_key";

    public String generateToken(String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
                .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error generating JWT token", exception);
        }
    }

    public String validateTokenAndRetrieveSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Invalid JWT token", exception);
        }
    }
}