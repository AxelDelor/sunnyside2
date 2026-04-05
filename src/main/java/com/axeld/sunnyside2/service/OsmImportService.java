package com.axeld.sunnyside2.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.axeld.sunnyside2.dto.OsmElement;
import com.axeld.sunnyside2.dto.OsmResponse;
import com.axeld.sunnyside2.model.Bar;
import com.axeld.sunnyside2.repository.BarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OsmImportService {

  private final RestTemplate restTemplate;
  private final BarRepository barRepository;
  private static final String query = "[out:json][timeout:60][bbox:50.55,2.95,50.70,3.15];\r\n" + //
      "(node[\"amenity\"=\"bar\"];\r\n" + //
      "node[\"amenity\"=\"pub\"];\r\n" + //
      "node[\"amenity\"=\"cafe\"];\r\n" + //
      "node[\"amenity\"=\"restaurant\"]);\r\n" + //
      "out body;";

  public void importBars() {

  Integer barCount = 0;

    try {
      OsmResponse response = restTemplate.postForObject("https://overpass-api.de/api/interpreter", query, OsmResponse.class);

      for (OsmElement element : response.getElements()) {
        String name = element.getTags().getOrDefault("name", "Nom inconnu");
        Double lat = element.getLat();
        Double lon = element.getLon();

        Bar bar = new Bar();
        bar.setName(name);
        bar.setLatitude(lat);
        bar.setLongitude(lon);
        bar.setSunny(false);

        barRepository.save(bar);
        barCount++;
        System.out.println(bar.getName() + " créé");
      }
      System.out.println(barCount + " bars créés");

    } catch (NullPointerException e) {
      System.out.println("Erreur lors de l'appel API OSM");
    }

  }

}
