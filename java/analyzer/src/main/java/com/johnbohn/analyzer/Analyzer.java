package com.johnbohn.analyzer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    int threadPoolSize = Runtime.getRuntime().availableProcessors() * 2;
    ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

    while (!this.ids.isEmpty()) {
      Worker worker = new Worker(this.ids, this.accumulation, this.api);
      executor.execute(worker);
    }
  }
}
