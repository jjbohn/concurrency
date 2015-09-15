package com.johnbohn.analyzer;

import java.util.HashMap;

/**
 * Holds a HashMap that stores key value pairs of the username
 * and the number of comments.
 */
public class Accumulation {
  HashMap<String, Integer> users = new HashMap<String, Integer>();

  public void post(Item item) {
    if (item.hasBy()) {
      String by = item.getBy();

      if (users.containsKey(by)) {
        users.put(by, users.get(by) + 1);
      } else {
        users.put(by, 1);
      }
    }
  }
}
