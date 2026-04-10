package com.axeld.sunnyside2.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.axeld.sunnyside2.dto.LoginRequest;
import com.axeld.sunnyside2.dto.RegisterRequest;
import com.axeld.sunnyside2.model.User;
import com.axeld.sunnyside2.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final JwtService jwtService;

  @Override
  public UserDetails loadUserByUsername(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
  }

  public User saveUser(RegisterRequest registerRequest) {

    User user = new User();
    user.setName(registerRequest.getName());
    user.setEmail(registerRequest.getEmail());
    user.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));

    return userRepository.save(user);

  }

  public String loginUser(LoginRequest loginRequest) {
    Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());

    if (user.isPresent()) {

      if (bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
        return jwtService.generateToken(user.get());
      } else {
        throw new RuntimeException("Identifiants incorrects");
      }

    } else {
      throw new RuntimeException("Utilisateur introuvable");

    }
  }

}
