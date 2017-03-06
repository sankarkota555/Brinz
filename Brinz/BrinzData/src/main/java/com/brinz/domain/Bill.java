package com.brinz.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "BRINZ_BILL")
@DynamicInsert
public class Bill implements Serializable {

  private static final long serialVersionUID = -1004192530915664302L;

  @Id
  @Column(name = "bill_id")
  @GenericGenerator(name = "inc", strategy = "increment")
  @GeneratedValue(generator = "inc")
  private Long billId;

  @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinColumn(name = "bill_id_fk")
  private List<SoldItem> soldItems;

  @Temporal(TemporalType.DATE)
  @Column(name = "bill_date", nullable = false)
  private Date billDate;

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  public Long getBillId() {
    return billId;
  }

  public void setBillId(Long billId) {
    this.billId = billId;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<SoldItem> getSoldItems() {
    return soldItems;
  }

  public void setSoldItems(List<SoldItem> soldItems) {
    this.soldItems = soldItems;
  }

  public Date getBillDate() {
    return billDate;
  }

  public void setBillDate(Date billDate) {
    this.billDate = billDate;
  }

}
