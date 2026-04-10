package com.axeld.sunnyside2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterRequest {

  private String name;
  private String email;
  private String password;

}
