package tree;

import java.io.Serializable;

/**
 * This class represents the basic of a node (file/directory) on shell
 *
 */
public class Node implements Serializable {

  /**
   * Stores name of the node
   */
  private String name;


  /**
   * stores path of node
   */
  private String path; // notice path must be full path


  /**
   * Default constructor
   */
  public Node() {}


  /**
   * Constructs node object and assigns to it its name and path in tree.
   * 
   * @param name Determines name of node
   * @param path Determines path of node
   */
  public Node(String name, String path) {
    this.name = name;
    this.path = path;
  }


  /**
   * Changes name of node to use desired name
   * 
   * @param name Determines new name for node
   */
  public void changeName(String name) {
    this.name = name;
  }


  /**
   * @return Path of node
   */
  public String getPath() {
    return this.path;
  }


  /**
   * @return Name of node
   */
  public String getName() {
    return this.name;
  }
}
