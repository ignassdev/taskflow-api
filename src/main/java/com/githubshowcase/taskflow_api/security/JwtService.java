package com.githubshowcase.taskflow_api.security;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET = "supersecretkeyforjwtsupersecretkeyforjwt";
    private static final long EXPIRATION = 1000 * 60 * 60; //1 hour
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(EXPIRATION)))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token){
        return Jwts.parser().verifyWith((SecretKey) key).build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }

    public boolean isTokenValid(String token){
        try{
            var claims = Jwts.parser().verifyWith((SecretKey) key).build()
                    .parseSignedClaims(token);
            return claims.getPayload().getExpiration().after(new Date());
        } catch (Exception e){
            return false;
        }
    }

}