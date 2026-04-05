package com.axeld.sunnyside2.controller;

import org.springframework.web.bind.annotation.RestController;

import com.axeld.sunnyside2.service.OsmImportService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequiredArgsConstructor
public class AdminController {

  private final OsmImportService osmImportService;

  @PostMapping("/api/admin/import")
  public ResponseEntity<String> postBars() {
    osmImportService.importBars();
    return ResponseEntity.ok("import terminé");
  }

}
