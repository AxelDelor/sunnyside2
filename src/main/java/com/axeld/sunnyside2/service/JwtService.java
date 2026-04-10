package com.axeld.sunnyside2.service;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.axeld.sunnyside2.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

  @Value("${jwt.secret}")
  private String secret;

  public String generateToken(User user){
    return Jwts.builder()
    .subject(user.getEmail())
    .expiration(new java.util.Date(System.currentTimeMillis() + 86400000)) // 24h
    .signWith(getSecretKey())
    .compact();

  }

  public SecretKey getSecretKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
  }

  public String extractEmail(String token) {
    return Jwts.parser()
    .verifyWith(getSecretKey())
    .build()
    .parseSignedClaims(token)
    .getPayload()
    .getSubject();
  }

}
