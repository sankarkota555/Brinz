package com.brinz.repository;

import org.springframework.data.repository.CrudRepository;

import com.brinz.domain.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Long> {

  Customer findByCustomerName();

}
