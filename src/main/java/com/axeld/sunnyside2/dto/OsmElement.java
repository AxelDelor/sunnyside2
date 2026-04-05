package com.axeld.sunnyside2.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OsmElement {

  private Long id;
  private Double lat;
  private Double lon;
  private Map<String, String> tags;

}
