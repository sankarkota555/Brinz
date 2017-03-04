package com.brinz.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public String getHomePage() {
    return "home";
  }

}