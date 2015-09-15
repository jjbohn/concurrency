package com.johnbohn.analyzer;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Api {
  public List<Integer> getRootIds() {
    List<Integer> ids = new ArrayList<Integer>();

    try {
      HttpResponse<JsonNode> jsonResponse = Unirest.get("https://hacker-news.firebaseio.com/v0/topstories.json").asJson();
      JSONArray json_ids = jsonResponse.getBody().getArray();
      for (int i = 0; i < json_ids.length(); i++) {
        ids.add(json_ids.getInt(i));
      }
    } catch (UnirestException e) {
      e.printStackTrace();
    }

    return ids;
  }

  public Item getItem(Integer id) throws UnirestException {
    String url = String.format("https://hacker-news.firebaseio.com/v0/item/%d.json", id);
    HttpResponse<JsonNode> jsonResponse = Unirest.get(url).asJson();

    return new Item(jsonResponse.getBody().getObject());
  }
}
