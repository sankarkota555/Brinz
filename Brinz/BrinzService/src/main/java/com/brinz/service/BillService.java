package com.brinz.service;

import com.brinz.domain.Bill;

public interface BillService {

  /**
   * Saves bill into DB.
   * 
   * @param bill
   *          {@link Bill} object to save
   * @return saved bill ID
   */
  Long saveBill(Bill bill);
  
  /**
   * Gives bill based on given ID
   * @param billId {@link Bill} ID
   * @return {@link Bill} object
   */
  Bill getBillById(Long billId);

}
