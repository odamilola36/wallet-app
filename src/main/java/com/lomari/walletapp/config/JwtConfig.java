package com.lomari.walletapp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtConfig {
    private final String  SECRET_KEY;

    private final JwtData jwtData;


    public JwtConfig(JwtData jwtData) {
        SECRET_KEY = jwtData.getSecret();
        this.jwtData = jwtData;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails user, boolean isAccess){
        Map<String, Object> map = new HashMap<>();
        map.put("ROLES", user.getAuthorities());
        return isAccess ? createAccessToken(user, map) : createRefreshToken(user);
    }

    private String createRefreshToken(UserDetails user) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 60 * jwtData.getRefreshTokenExpiry()))
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    private String createAccessToken(UserDetails user, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 60 * jwtData.getAuthTokenExpiry()))
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
    private <T> T extractClaim(String token, Function<Claims, T> function ) {
        final Claims claims = extractAllClaims(token);
        return function.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
