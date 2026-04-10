package com.axeld.sunnyside2.service;

import org.springframework.stereotype.Service;

import com.axeld.sunnyside2.model.Bar;
import com.axeld.sunnyside2.repository.BarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BarService {

  private final BarRepository barRepository;

  public Iterable<Bar> getBars() {
    return barRepository.findAll();
  }

}
