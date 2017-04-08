package com.brinz.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.brinz.DTO.CustomerDTO;
import com.brinz.domain.Customer;

@Component
public class BrinzMapper {

  /**
   * Maps given {@link Customer} to {@link CustomerDTO}
   * 
   * @param customer
   *          {@link Customer} object
   * @return mapped {@link CustomerDTO} object
   */
  public CustomerDTO mapCustomerToDto(Customer customer) {

    return mapToDto(customer);
  }

  /**
   * Maps given list of {@link Customer} to list of {@link CustomerDTO}
   * 
   * @param customers
   *          list of {@link Customer} object
   * @return lis of mapped {@link CustomerDTO} object
   */
  public List<CustomerDTO> mapCustomerToDto(List<Customer> customers) {
    List<CustomerDTO> cutomersList = new ArrayList<>();
    for (Customer customer : customers)
      cutomersList.add(mapToDto(customer));
    return cutomersList;
  }

  private CustomerDTO mapToDto(Customer customer) {
    return new CustomerDTO(customer.getCustomerId(), customer.getCustomerName(),
        customer.getPhone(), customer.getCreditAmoount());
  }

}
