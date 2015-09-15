package com.johnbohn.analyzer;

import java.util.List;

/**
 * Gets the top story root ids and starts traversing the graph
 * with workers.
 */
class Analyzer {
  private Accumulation accumulation;
  private Api api;
  private List<Integer> ids;

  public Analyzer(Accumulation accumulation, Api api) {
    this.accumulation = accumulation;
    this.api = api;
  }

  public void analyze() {
    this.ids = this.api.getRootIds();
    this.traverse();
  }

  public void traverse() {
    Integer count = 0;

    while (!this.ids.isEmpty()) {
      System.out.println(++count);

      Worker worker = new Worker(this.ids, this.accumulation, this.api);
      worker.run();
    }
  }
}
