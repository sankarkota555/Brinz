package com.brinz.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "BRINZ_users")
@DynamicUpdate
public class BrinzUser implements Serializable {

  private static final long serialVersionUID = -6566053542294070958L;

  @Id
  @Column(name = "user_id")
  @GenericGenerator(name = "inc", strategy = "increment")
  @GeneratedValue(generator = "inc")
  private Integer userId;

  @Column(name = "user_name", unique = true, nullable = false, length = 75)
  private String userName;

  @Column(name = "password", length = 50)
  private String password;

  @Column(name = "role", length = 20)
  private String role;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

}
