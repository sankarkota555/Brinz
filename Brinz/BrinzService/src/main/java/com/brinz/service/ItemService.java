package com.brinz.service;

import java.util.List;

import com.brinz.domain.Item;

public interface ItemService {

  /**
   * Saves given item into DB.
   * 
   * @param item
   *          {@link Item} to be saved
   * @return saved item ID.
   */
  Integer saveItem(Item item);

  /**
   * Searches for item by item name
   * 
   * @param searchQuery
   *          query for search
   * @return list of {@link Item}s
   */
  List<Item> searchItemsByName(String searchQuery);

}
