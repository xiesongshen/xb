package com.xss.entity;


import java.io.Serializable;

public class Menu implements Serializable {
  private static final long serialVersionUID= 1L;

  private Integer id;
  private Integer pId;
  private Integer type;
  private String name;
  private String url;
  private Integer orderBy;

  public Menu() {
  }

  public Menu(Integer id, Integer pId, Integer type, String name, String url, Integer orderBy) {
    this.id = id;
    this.pId = pId;
    this.type = type;
    this.name = name;
    this.url = url;
    this.orderBy = orderBy;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getpId() {
    return pId;
  }

  public void setpId(Integer pId) {
    this.pId = pId;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Integer getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(Integer orderBy) {
    this.orderBy = orderBy;
  }
}
