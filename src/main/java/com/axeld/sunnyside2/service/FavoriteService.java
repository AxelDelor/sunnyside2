package com.axeld.sunnyside2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.axeld.sunnyside2.model.Favorite;
import com.axeld.sunnyside2.model.User;
import com.axeld.sunnyside2.repository.FavoriteRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteService {

  private final FavoriteRepository favoriteRepository;

  public List<Favorite> getFavorites(User user) {
    return favoriteRepository.findByUser(user);
  }

  public Favorite saveFavorite(Favorite favorite) {
    if (alreadyExists(favorite)) {
      System.out.println("Le bar existe déjà");
      return null;
    } else {
      Favorite savedFavorite = favoriteRepository.save(favorite);
      return savedFavorite;
    }
  }

  public Boolean alreadyExists(Favorite favorite) {
    return favoriteRepository.findByUserAndBar(favorite.getUser(), favorite.getBar()).isPresent();
  }

  @Transactional
  public void deleteFavorite(Long id, User user) {
    favoriteRepository.deleteByIdAndUser(id, user);
  }
}
