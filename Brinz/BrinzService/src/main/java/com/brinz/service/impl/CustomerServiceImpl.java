package com.brinz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.brinz.domain.Customer;
import com.brinz.repositories.CustomerRepository;
import com.brinz.service.CustomerService;

@Service
@Lazy
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public Long saveCustomer(Customer customer) {
    return customerRepository.save(customer).getCustomerId();
  }

}
