package com.brinz.repositories;

import org.springframework.data.repository.CrudRepository;

import com.brinz.domain.BrinzUser;

public interface UserRepository extends CrudRepository<BrinzUser, Integer> {

  BrinzUser findByUserName(String userName);

}
