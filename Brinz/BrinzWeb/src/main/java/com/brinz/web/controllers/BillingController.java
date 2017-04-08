package com.brinz.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brinz.domain.Bill;
import com.brinz.service.BillService;

@RestController
public class BillingController {
  
  
  private static final Logger log = LoggerFactory.getLogger(BillingController.class);


  @Autowired
  private BillService billService;

  @RequestMapping(value = "/saveBill", method = RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_UTF8_VALUE})
  public Long saveBill(@RequestBody Bill bill) {
    log.info("bill in controller:{} ", bill);
    log.info("customer : {}", bill.getCustomer()); 
    log.info("bill sold items: {}", bill.getSoldItems());
    return billService.saveBill(bill);
  }

}
