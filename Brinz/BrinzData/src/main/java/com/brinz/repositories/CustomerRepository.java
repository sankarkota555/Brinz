package com.brinz.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.brinz.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

  List<Customer> findByCustomerNameStartingWith(String name);

}
