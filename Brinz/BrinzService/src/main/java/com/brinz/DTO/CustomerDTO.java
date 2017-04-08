package com.brinz.DTO;

import java.io.Serializable;

public class CustomerDTO implements Serializable {

  private static final long serialVersionUID = -6513297767546841775L;

  private Long customerId;

  private String customerName;

  private String phone;

  private Long creditAmoount;

  public CustomerDTO() {

  }

  public CustomerDTO(Long customerId, String customerName, String phone, Long creditAmoount) {
    this.customerId = customerId;
    this.customerName = customerName;
    this.phone = phone;
    this.creditAmoount = creditAmoount;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Long getCreditAmoount() {
    return creditAmoount;
  }

  public void setCreditAmoount(Long creditAmoount) {
    this.creditAmoount = creditAmoount;
  }

}
