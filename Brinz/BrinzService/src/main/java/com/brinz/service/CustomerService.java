package com.brinz.service;

import java.util.List;

import com.brinz.DTO.CustomerDTO;
import com.brinz.domain.Customer;

public interface CustomerService {

  /**
   * Saves given customer into DB.
   * 
   * @param customer
   *          {@link Customer} to be saved.
   * @return saved customer ID.
   */
  Long saveCustomer(Customer customer);

  /**
   * Finds customer by name.
   * @param name search name
   * @return {@link List} of {@link CustomerDTO}
   */
  List<CustomerDTO> findCustomersByName(String name);

}
