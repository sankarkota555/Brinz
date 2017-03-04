package com.brinz.repositories;

import org.springframework.data.repository.CrudRepository;

import com.brinz.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

  User findByUserName(String userName);

}
