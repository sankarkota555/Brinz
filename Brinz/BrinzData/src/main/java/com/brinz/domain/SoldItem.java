package com.brinz.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sold_Item")
public class SoldItem implements Serializable {

  private static final long serialVersionUID = 7302069564698380875L;

  @Id
  @Column(name = "sild_item_id")
  @GenericGenerator(name = "inc", strategy = "increment")
  @GeneratedValue(generator = "inc")
  private Long soldItemId;

  @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinColumn(name = "item_id_fk")
  private Item item;

  @Column(name = "sold_quantity", nullable = false)
  private Integer soldQuantity;

  @Column(name = "sold_price", nullable = false)
  private Integer soldPrice;

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

}
