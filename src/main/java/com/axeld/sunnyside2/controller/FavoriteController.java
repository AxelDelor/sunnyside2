package com.axeld.sunnyside2.controller;

import org.springframework.web.bind.annotation.RestController;

import com.axeld.sunnyside2.model.Favorite;
import com.axeld.sunnyside2.model.User;
import com.axeld.sunnyside2.service.FavoriteService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class FavoriteController {

  private final FavoriteService favoriteService;

  @PostMapping("/favorite")
  public Favorite createFavorite(@RequestBody Favorite favorite) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    favorite.setUser(user);
    return favoriteService.saveFavorite(favorite);
  }

}
