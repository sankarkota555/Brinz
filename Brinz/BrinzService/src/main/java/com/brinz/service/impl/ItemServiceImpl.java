package com.brinz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brinz.domain.Item;
import com.brinz.repositories.ItemRepository;
import com.brinz.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

  @Autowired
  ItemRepository itemRepository;

  @Override
  public Integer saveItem(Item item) {
    return itemRepository.save(item).getItemId();
  }

  @Override
  public List<Item> searchItemsByName(String searchQuery) {
    return itemRepository.findItemsByName(searchQuery.toLowerCase());
  }

}
