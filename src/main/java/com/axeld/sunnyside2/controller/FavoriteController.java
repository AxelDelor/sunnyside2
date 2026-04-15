package com.axeld.sunnyside2.controller;

import org.springframework.web.bind.annotation.RestController;

import com.axeld.sunnyside2.model.Favorite;
import com.axeld.sunnyside2.model.User;
import com.axeld.sunnyside2.service.FavoriteService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
public class FavoriteController {

  private final FavoriteService favoriteService;

  @GetMapping("/favorites")
  public List<Favorite> getFavorites() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return favoriteService.getFavorites(user);
  }

  @PostMapping("/favorite")
  public Favorite createFavorite(@RequestBody Favorite favorite) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    favorite.setUser(user);
    return favoriteService.saveFavorite(favorite);
  }

  @DeleteMapping("/favorite/{id}")
  public void deleteFavorite(@PathVariable Long id) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    favoriteService.deleteFavorite(id, user);
  }

}
