package com.brinz.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brinz.domain.Item;
import com.brinz.service.ItemService;

@RestController
public class ItemController {

  @Autowired
  private ItemService itemService;

  @RequestMapping(value = "findItemByName")
  public List<Item> findItemByName(String name) {
    return itemService.searchItemsByName(name);
  }

  @RequestMapping(value = "findAllItems")
  public Iterable<Item> findAllItems() {
    return itemService.findAllItems();
  }

}
