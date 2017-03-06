package com.brinz.repositories;

import org.springframework.data.repository.CrudRepository;

import com.brinz.domain.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Long> {

  Customer findByCustomerName(String customerName);

}
