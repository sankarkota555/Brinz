package com.brinz.repositories;

import org.springframework.data.repository.CrudRepository;

import com.brinz.domain.Bill;

public interface BillRepository extends CrudRepository<Bill, Long> {

  Bill findByBillId();

}
