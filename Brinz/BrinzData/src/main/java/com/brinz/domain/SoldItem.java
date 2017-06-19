package com.brinz.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "BRINZ_SOLD_ITEM")
public class SoldItem implements Serializable {

  private static final long serialVersionUID = 7302069564698380875L;

  @Id
  @Column(name = "SILD_ITEM_ID")
  @GenericGenerator(name = "inc", strategy = "increment")
  @GeneratedValue(generator = "inc")
  private Long soldItemId;

  @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinColumn(name = "ITEM_ID_FK")
  private Item item;

  @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinColumn(name = "CUSTOMER_ID_FK")
  private Customer customer;

  @Column(name = "SOLD_QUANTITY", nullable = false)
  private Integer soldQuantity;

  @Column(name = "SOLD_PRICE", nullable = false)
  private Integer soldPrice;

  @Column(name = "NUM_OF_BAGS", nullable = false)
  private Integer noOfBags;

  @Temporal(TemporalType.DATE)
  @Column(name = "SOLD_DATE", nullable = false)
  private Date soldDate;

  @Temporal(TemporalType.DATE)
  @Column(name = "PAID_DATE")
  private Date paidDate;

  public Long getSoldItemId() {
    return soldItemId;
  }

  public void setSoldItemId(Long soldItemId) {
    this.soldItemId = soldItemId;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public Integer getSoldQuantity() {
    return soldQuantity;
  }

  public void setSoldQuantity(Integer soldQuantity) {
    this.soldQuantity = soldQuantity;
  }

  public Integer getSoldPrice() {
    return soldPrice;
  }

  public void setSoldPrice(Integer soldPrice) {
    this.soldPrice = soldPrice;
  }

  public Integer getNoOfBags() {
    return noOfBags;
  }

  public void setNoOfBags(Integer noOfBags) {
    this.noOfBags = noOfBags;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Date getSoldDate() {
    return soldDate;
  }

  public void setSoldDate(Date soldDate) {
    this.soldDate = soldDate;
  }

  public Date getPaidDate() {
    return paidDate;
  }

  public void setPaidDate(Date paidDate) {
    this.paidDate = paidDate;
  }

}
