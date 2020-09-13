package test;

import java.util.ArrayList;
import tree.ParentTree;

public class MockTree extends ParentTree {


  public MockTree() {// implements Tree
    // Creates root blah blah
  }

  public String getFileContents(String path) {
    // Acts like it calls and grabs information at path from node
    if (path.equals("/valid")) {
      return "Obtains data from file";
    }

    return "invalid path";
  }

  public boolean validateFileExistence(String path) {
    if (path.equals("/valid")) {
      return true;
    }
    return false;
  }


}
