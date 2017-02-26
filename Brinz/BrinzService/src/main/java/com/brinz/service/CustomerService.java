package com.brinz.service;

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

}
