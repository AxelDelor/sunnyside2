package com.axeld.sunnyside2.controller;

import org.springframework.web.bind.annotation.RestController;

import com.axeld.sunnyside2.model.Bar;
import com.axeld.sunnyside2.service.BarService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequiredArgsConstructor
public class BarController {

private final BarService barService;

@GetMapping("/bars")
public Iterable<Bar>  getBars() {
    return barService.getBars();
}


}
