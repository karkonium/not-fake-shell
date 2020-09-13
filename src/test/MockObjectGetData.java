package test;

import Networking.DataFetcher;

public class MockObjectGetData implements DataFetcher {

  public MockObjectGetData() {}

  @Override
  public String getDataFromUrl(String path) throws Exception {
    try {
      System.out.println("hello");
      if (path.equals("/validpath")) {
        return "text on the site";
      } else {
        return "Invalid path";
      }
    } catch (Exception e) {
      return "";
    }
  }

}
