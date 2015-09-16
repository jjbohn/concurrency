package com.johnbohn.analyzer;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Holds a HashMap that stores key value pairs of the username
 * and the number of comments.
 */
public class Accumulation {
  ConcurrentHashMap<String, Integer> users = new ConcurrentHashMap<String, Integer>();

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
