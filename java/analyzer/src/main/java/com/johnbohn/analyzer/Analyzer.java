package com.johnbohn.analyzer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

  public void analyze() throws InterruptedException {
    this.ids = this.api.getRootIds();
    this.traverse();
  }

  public void traverse() throws InterruptedException {
    int threadPoolSize = Runtime.getRuntime().availableProcessors();
    ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

    for (Integer id: this.ids) {
      Worker worker = new Worker(id, this.accumulation, this.api);
      executor.execute(worker);
    }

    executor.shutdown();
    executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
  }
}
