package com.example.itemservice.domain.item;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Item {

  private Long id;
  private String name;
  private Integer price;
  private Integer quantity;

  public Item(String name, Integer price, Integer quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

}
