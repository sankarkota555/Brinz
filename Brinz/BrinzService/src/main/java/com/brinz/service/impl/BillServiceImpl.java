package com.brinz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brinz.domain.Bill;
import com.brinz.domain.Customer;
import com.brinz.domain.Item;
import com.brinz.domain.SoldItem;
import com.brinz.repositories.BillRepository;
import com.brinz.repositories.CustomerRepository;
import com.brinz.repositories.ItemRepository;
import com.brinz.service.BillService;

@Service
public class BillServiceImpl implements BillService {

  private static final Logger log = LoggerFactory.getLogger(BillServiceImpl.class);

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private BillRepository billRepository;

  @Override
  @Transactional
  public Long saveBill(Bill bill) {
    Customer customer = null;
    // if customer exists, then load from DB
    if (bill.getCustomer().getCustomerId() != null) {
      log.info("Loading customer from DB with id: {}", bill.getCustomer().getCustomerId());
      customer = customerRepository.findOne(bill.getCustomer().getCustomerId());
    } else {
      // create new customer object to save in DB
      customer = new Customer();
      customer.setCustomerName(bill.getCustomer().getCustomerName());
      customer.setPhone(bill.getCustomer().getPhone());
      customer.setCreditAmoount(0L);
      log.info("creating new customer");
    }
    // set customer to bill
    bill.setCustomer(customer);

    SoldItem billedItem;
    Item item;
    List<SoldItem> soldItemsList = new ArrayList<>();
    for (SoldItem soldItem : bill.getSoldItems()) {
      billedItem = new SoldItem();
      // if item exists load from DB
      if (soldItem.getItem().getItemId() != null) {
        log.info("loading Item fromDB with Id:{}", soldItem.getItem().getItemId());
        item = itemRepository.findOne(soldItem.getItem().getItemId());
      } else {
        // create new item object to save in DB
        item = new Item();
        item.setItemName(soldItem.getItem().getItemName());
        item.setPrice(soldItem.getItem().getPrice());
        log.info("creating new Item");
      }
      billedItem.setItem(item);
      billedItem.setSoldPrice(soldItem.getSoldPrice());
      billedItem.setSoldQuantity(soldItem.getSoldQuantity());
      billedItem.setNoOfBags(soldItem.getNoOfBags());

      soldItemsList.add(billedItem);
    }
    // set sold item details to bill
    bill.setSoldItems(soldItemsList);
    // set bill generated date
    bill.setBillDate(new Date());

    log.info("Now saving bill");
    bill = billRepository.save(bill);
    log.info("Bill saved with id:{}", bill.getBillId());
    return bill.getBillId();
  }

  @Override
  public Bill getBillById(Long billId) {
    return billRepository.findOne(billId);
  }

}
