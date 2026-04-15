package com.axeld.sunnyside2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.axeld.sunnyside2.model.Bar;
import com.axeld.sunnyside2.model.Favorite;
import com.axeld.sunnyside2.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Long> {

  List<Favorite> findByUser(User user);

  Optional<Favorite> findByUserAndBar(User user, Bar bar);

  void deleteByIdAndUser(Long id, User user);
}
