package com.brinz.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.brinz.domain.Item;

public interface ItemRepository extends CrudRepository<Item, Integer> {

  List<Item> findByItemNameStartingWith(String itemName);

}
