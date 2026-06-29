package com.vaibhavi.spendwise.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Map;
import java.util.function.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.core.userdetails.UserDetails;
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;
    public String generateToken(UserDetails userDetails) {

        return createToken(
                new HashMap<>(),
                userDetails.getUsername());
    }
    private String createToken(
            Map<String, Object> claims,
            String username) {

    	Key key = Keys.hmacShaKeyFor(
    	        secretKey.getBytes(StandardCharsets.UTF_8));

    	return Jwts.builder()
    	        .setClaims(claims)
    	        .setSubject(username)
    	        .setIssuedAt(new Date())
    	        .setExpiration(
    	                new Date(
    	                        System.currentTimeMillis()
    	                                + jwtExpiration))
    	        .signWith(key, SignatureAlgorithm.HS256)
    	        .compact();
    }
    private static final long serialVersionUID = 1L;
    public class UserNotFoundException
    extends RuntimeException {

private static final long serialVersionUID = 1L;

public UserNotFoundException(String message) {
    super(message);
}
}
    public String extractUsername(String token) {

        return extractClaim(
                token,
                Claims::getSubject);
    }
    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver) {

        final Claims claims =
                extractAllClaims(token);

        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {

        Key key = Keys.hmacShaKeyFor(
                secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public Date extractExpiration(
            String token) {

        return extractClaim(
                token,
                Claims::getExpiration);
    }
    private Boolean isTokenExpired(
            String token) {

        return extractExpiration(token)
                .before(new Date());
    }
    public Boolean validateToken(
            String token,
            UserDetails userDetails) {

        final String username =
                extractUsername(token);

        return username.equals(
                userDetails.getUsername())
                &&
                !isTokenExpired(token);
    }
}