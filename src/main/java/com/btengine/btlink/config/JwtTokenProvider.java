package com.btengine.btlink.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final String jwtSecretKey = "4aV54a55fD54a6545454D5F54654a554a454a554a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a454a4";

    public String generateJwtToken(String accountNumber, String userId) {
        SecretKey secretKey = key();
        long expirationTime = 1000 * 60 * 60;
        Date issuedAt = new Date();
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        try {
            String token = Jwts.builder()
                    .subject(accountNumber)
                    .claim("name", userId)
                    .issuedAt(issuedAt)
                    .expiration(expirationDate)
                    .signWith(secretKey)
                    .compact();

            return token;
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate JWT token", e);
        }
    }

    public SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
    }

    public String getUsername(String token){

        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // validate JWT token
    public boolean validateToken(String token){
        Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parse(token);
        return true;

    }
}
