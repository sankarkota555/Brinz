package com.brinz.repository;

import org.springframework.data.repository.CrudRepository;

import com.brinz.domain.Item;

public interface ItemRepository extends CrudRepository<Item, Integer>{
  
  Item findByItemName();

}
