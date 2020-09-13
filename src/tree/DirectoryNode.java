package tree;

import java.util.ArrayList;

/**
 * This class is a type of node that has a properties of a folder
 *
 */
public class DirectoryNode extends Node {

  /**
   * An array that holds nodes for everything that the DirectoryNodes contains
   */
  private ArrayList<Node> childList;


  /**
   * Constructs a directory object that has a name and a list to contain everything it holds.
   * 
   * @param name Determines name of the directory node
   * @param path Determines path for the directory node
   */
  public DirectoryNode(String name, String path) {
    super(name, path);
    childList = new ArrayList<Node>();
  }


  /**
   * Adds another node to the list thats holds contents of directory node
   * 
   * @param node The node that needs to be added to directory contents
   */
  public void addToList(Node node) {
    childList.add(node);
  }


  /**
   * Purpose of this method is to return the list that holds the contents of the directory
   * 
   * @return An ArrayList that contains contents of directory
   */
  public ArrayList<Node> obtainList() {
    return childList;
  }


  /**
   * The purpose of this method is to remove a certain file/directory that is in the contents of the
   * directory.
   * 
   * @param toRemove Determines the file/directory that needs to be removed
   */
  public void removeNodeFromList(String toRemove) {
    for (Node child : childList) {
      if (child.getName().equals(toRemove)) {
        childList.remove(child);
        break;
      }
    }
  }


  /**
   * Purpose of this method is to create and return an ArrayList containing the names of the files
   * and directories that the Directory object contains.
   * 
   * @return The list of directory contents in string form
   */
  public ArrayList<String> obtainChildStringList() {
    
    ArrayList<String> childStringList = new ArrayList<String>();
    
    for (int i = 0; i < childList.size(); i++) {
      childStringList.add(childList.get(i).getName());
    }
    return childStringList;
  }
}
