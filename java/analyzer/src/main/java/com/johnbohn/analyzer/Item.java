package com.johnbohn.analyzer;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents a Hacker news item. It has two main values,
 * "by" and "kids". "by" is the name of the user of an item.
 * "kids" is an array of integers. The integers are the ids
 * of the children.
 */
public class Item {
  private JSONObject jsonObj;

  public Item(JSONObject jsonObj) {
    this.jsonObj = jsonObj;
  }

  public Boolean hasBy() { return jsonObj.has("by"); }
  public String getBy() { return jsonObj.getString("by"); }

  public Boolean hasKids() { return jsonObj.has("kids"); }
  public Integer[] getKids() {
    JSONArray jsonKids = jsonObj.getJSONArray("kids");
    Integer[] kids = new Integer[jsonKids.length()];

    for (int i = 0; i < jsonKids.length(); i++) {
      kids[i] = jsonKids.getInt(i);
    }

    return kids;
  }
}
