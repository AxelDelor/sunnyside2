package com.axeld.sunnyside2.controller;

import org.springframework.web.bind.annotation.RestController;

import com.axeld.sunnyside2.dto.LoginRequest;
import com.axeld.sunnyside2.dto.RegisterRequest;
import com.axeld.sunnyside2.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @Value("${allow.registration:true}")
  private boolean allowRegistration;

  @PostMapping("/api/auth/register")
  public ResponseEntity<String> register(@RequestBody RegisterRequest entity) {
    if (!allowRegistration) {
        return ResponseEntity.status(403).body("Inscription désactivée");
    }
    authService.saveUser(entity);
    return ResponseEntity.ok("Vous êtes maintenant inscrit");
  }

  @PostMapping("/api/auth/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest entity) {
    return ResponseEntity.ok(authService.loginUser(entity));
  }
}
