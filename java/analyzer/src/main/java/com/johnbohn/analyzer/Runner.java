package com.johnbohn.analyzer;

/**
 * The main class that kicks the whole thing off.
 */
public class Runner {
  public static void main(String[] args) {
    Accumulation accumulation = new Accumulation();
    Api api = new Api();

    Analyzer analyzer = new Analyzer(accumulation, api);
    analyzer.analyze();
  }
}

