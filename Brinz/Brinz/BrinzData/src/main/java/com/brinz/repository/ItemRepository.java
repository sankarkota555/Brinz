package com.brinz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brinz.domain.Item;

public interface ItemRepository extends CrudRepository<Item, Integer> {

  @Query("From Item item where lower(item.itemName) like :itemName%")
  List<Item> findItemsByName(@Param("itemName") String searchQuery);

}
