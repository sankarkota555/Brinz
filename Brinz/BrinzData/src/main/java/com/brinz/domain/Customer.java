package com.brinz.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "BRINZ_CUSTOMER")
@DynamicInsert
@DynamicUpdate
public class Customer implements Serializable {

  private static final long serialVersionUID = -6904224747902137198L;

  @Id
  @Column(name = "customer_id")
  @GenericGenerator(name = "inc", strategy = "increment")
  @GeneratedValue(generator = "inc")
  private Long customerId;

  @Column(name = "customer_name", length = 75, nullable = false)
  private String customerName;

  @Column(name = "phone_number", length = 13, unique = true)
  private Integer phone;

  @Column(name = "credit_Amoount")
  private Long creditAmoount;

  @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
  private List<Bill> bills;

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

  public Integer getPhone() {
    return phone;
  }

  public void setPhone(Integer phone) {
    this.phone = phone;
  }

  public Long getCreditAmoount() {
    return creditAmoount;
  }

  public void setCreditAmoount(Long creditAmoount) {
    this.creditAmoount = creditAmoount;
  }

}
