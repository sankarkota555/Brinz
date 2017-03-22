package com.brinz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brinz.domain.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Long> {

  Customer findByCustomerName(String customerName);
  
  @Query("from Customer where lower(customerName) like :name%")
  List<Customer> findCustomersByName(@Param("name") String name);

}
