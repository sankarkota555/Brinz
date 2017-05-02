package com.brinz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.brinz.DTO.CustomerDTO;
import com.brinz.domain.Customer;
import com.brinz.mapper.BrinzMapper;
import com.brinz.repositories.CustomerRepository;
import com.brinz.service.CustomerService;

@Service
@Lazy
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private BrinzMapper brinzMapper;

  @Override
  public Long saveCustomer(Customer customer) {
    return customerRepository.save(customer).getCustomerId();
  }

  @Override
  public List<CustomerDTO> findCustomersByName(String name) {
    return brinzMapper.mapCustomerToDto(customerRepository.findByCustomerNameStartingWith(name.toLowerCase()));
  }

}
