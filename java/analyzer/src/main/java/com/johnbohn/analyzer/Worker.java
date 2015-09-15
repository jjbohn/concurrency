package com.johnbohn.analyzer;

import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;

/**
 * Traverses the graph of items and notifies the Accumulation of
 * of all nodes.
 */
public class Worker {
  private List<Integer> ids;
  private Accumulation accumulation;
  private Api api;

  public Worker(List<Integer> ids, Accumulation accumulation, Api api) {
    this.ids = ids;
    this.accumulation = accumulation;
    this.api = api;
  }

  public void run() {
    Integer id = this.ids.remove(0);

    try {
      Item item = this.accumulateById(id);

      if (item.hasKids()) {
        Integer[] child_ids = item.getKids();

        for (int i = 0; i < child_ids.length; i++) {
          this.ids.add(child_ids[i]);
        }
      }
    } catch (UnirestException e) {
      e.printStackTrace();
    }
  }

  private Item accumulateById(int id) throws UnirestException {
    Item item = api.getItem(id);

    this.accumulation.post(item);

    return item;
  }
}
