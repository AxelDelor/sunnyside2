package com.axeld.sunnyside2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.axeld.sunnyside2.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
