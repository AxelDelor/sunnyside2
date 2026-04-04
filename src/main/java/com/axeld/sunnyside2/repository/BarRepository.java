package com.axeld.sunnyside2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.axeld.sunnyside2.model.Bar;

@Repository
public interface BarRepository extends CrudRepository<Bar, Long> {

}
