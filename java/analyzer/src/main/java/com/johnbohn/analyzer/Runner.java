package com.johnbohn.analyzer;

/**
 * The main class that kicks the whole thing off.
 */
public class Runner {
  public static void main(String[] args) {
    Accumulation accumulation = new Accumulation(); // The accumulation of count of posts (Map, keyed by name, value is
    Api api = new Api(); // Interface to the hacker news API

    Analyzer analyzer = new Analyzer(accumulation, api);
    analyzer.analyze();
    System.exit(0);
  }
}

