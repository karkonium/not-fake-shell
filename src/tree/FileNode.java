package tree;

/**
 * This class is a type of node that has a file's property i.e text and name of file
 *
 */
public class FileNode extends Node {

  
  /**
   * Field that stores the string of data on the file
   */
  private String data;

  
  /**
   * Default constructor
   */
  public FileNode() {}

  
  /**
   * Constructs an object of type File that has a name, its own path in the tree, and data that will
   * be stored on the file.
   * 
   * @param name Determines name of the file
   * @param path Determines path of the file
   * @param data Determines data that the file will store
   */
  public FileNode(String name, String path, String data) {
    super(name, path);
    this.data = data;
  }


  /**
   * Constructs an object of type File that has a name, its own path in the tree.
   * 
   * @param name Determines name of the file
   * @param path Determines path of the file
   */
  public FileNode(String name, String path) {
    super(name, path);
    this.data = "";
  }


  /**
   * Returns data that the file stores
   * 
   * @return data on the file
   */
  public String getData() {
    return this.data;
  }


  /**
   * replace data stored on file
   * 
   * @param new contents to be stored to file
   */
  public void changeFileData(String newContents) {
    this.data = newContents;
  }
}
