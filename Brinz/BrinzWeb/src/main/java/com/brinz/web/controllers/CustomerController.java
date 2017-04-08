package com.brinz.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brinz.DTO.CustomerDTO;
import com.brinz.service.CustomerService;

@RestController
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @RequestMapping(value = "findCustomerByName")
  private List<CustomerDTO> findCutomers(@RequestParam String name) {
    return customerService.findCustomersByName(name);
  }

}
