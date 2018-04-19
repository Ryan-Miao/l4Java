package com.test.java.stream.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by Ryan Miao on 4/18/18.
 */
@Data
@AllArgsConstructor
@Builder
public class Dish {

  private String name;
  private boolean vegetarian;
  private int calories;
  private Type type;

  public enum Type {
    MEAT, FISH, OTHER
  }

}
