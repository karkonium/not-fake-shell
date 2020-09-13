package tree;

import java.util.ArrayList;
import display.Display;

public class ParentTree {

  static final long serialVersionUID = 0;
  /**
   * Root of the directory, otherwise known as starting directory
   */
  protected DirectoryNode root;

  /**
   * Current directory that the user is in
   */
  protected DirectoryNode cwd;

  /**
   * Constructor
   */
  public ParentTree() {
    root = new DirectoryNode("", "/");
    cwd = root;
  }

  protected static Display display = new Display();

  /**
   * Recursive method that traverses through the tree
   * 
   * @param path Path to the tree
   * @param depth How deep
   * @param treeText The data in a node
   * @return The data in the node
   */
  public String recursiveTree(String path, int depth, String treeText) {
    DirectoryNode root = getNodeForPath(path);
    ArrayList<Node> childList = root.obtainList();
    String tabList = "";

    for (int i = 0; i < depth; i++) {
      treeText += "\t";
      tabList += "\t";
    }
    if (!path.equals("/")) {
      treeText += root.getName() + "\n";
    }
    if (!childList.isEmpty()) {
      for (Node childNode : childList) {
        if (childNode instanceof DirectoryNode) {
          if (!path.equals("/")) {
            treeText =
                recursiveTree(getFullPath(path + "/" + childNode.getName()), depth + 1, treeText);
          } else {
            treeText = recursiveTree(getFullPath(path + childNode.getName()), depth + 1, treeText);
          }
        }
        if (childNode instanceof FileNode) {
          treeText += tabList + "\t" + childNode.getName() + System.lineSeparator();
        }
      }
    }
    return treeText;
  }


  /**
   * This method updates the cwd variable in the Tree
   * 
   * @param path The Current working directory
   */
  public void updateCWD(String path) {
    DirectoryNode newNode = getNodeForPath(path);

    if (newNode != null) {
      this.cwd = newNode;
    } else {
      display.standardOutputLine("Path not valid");
    }
  }


  /**
   * This method gets the cwd from the tree
   * 
   * @return The cwd
   */
  public String getCWD() {
    return cwd.getPath();
  }


  /**
   * This method gets the full path of a node
   * 
   * @param path A path to a node
   * @return A string which tells the full path
   */
  private String getFullPath(String path) {

    String finalPath = "";
    if (path.charAt(0) == '/') {
      finalPath = path;
    } else {
      if (this.getCWD().equals("/")) {
        finalPath = "/" + path;
      } else {
        finalPath = this.getCWD() + "/" + path;
      }
    }

    return finalPath;

  }

  /**
   * This method traverse the tree to get a directory node at a given path
   * 
   * @param path path to a directory node
   * @return a directory node
   */
  private DirectoryNode getNodeForPath(String path) {
    try {
      DirectoryNode workingNode = root;
      path = getFullPath(path);
      String[] pathList = path.split("/");
      int i = 1;

      while (i < pathList.length) {
        if (pathList[i].equals("..")) {
          workingNode = getParent(workingNode.getPath());
          i++;
        } else if (pathList[i].equals(".")) {
          // do nothing
          i++;
        } else {
          int wanted_index = workingNode.obtainChildStringList().indexOf(pathList[i]);
          // if (wanted_index != -1) {
          workingNode = (DirectoryNode) workingNode.obtainList().get(wanted_index);
          i++;
          /*
           * } else { // error msg bad path display.standardOutputLine("Bad path"); return null; }
           */
        }
      }
      return workingNode;
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * This method checks if the file exist at a given path
   * 
   * @param path A path to a node
   * @return True/False depending if the node is a file or not
   */
  public boolean validateFileExistence(String path) {
    if (path.equals("/") || path.equals(this.getCWD()))
      return true;
    path = getFullPath(path);
    String[] pathList = path.split("/");
    String target = pathList[pathList.length - 1];
    DirectoryNode parent = getParent(path);

    if (parent != null && parent.obtainChildStringList().contains(target)) {
      return true;
    }
    return false;
  }


  /**
   * Adds a directory node in the tree to the path specified.
   * 
   * @param path Path of node needing to be added
   * @exception e If the path does not exist
   */
  public void addDirectoryNode(String path) {
    try {
      String[] pathList = path.split("/");
      String name = pathList[pathList.length - 1];
      DirectoryNode workingNode = getParent(path);

      if (workingNode != null) {
        path = this.getFullPath(path);
        DirectoryNode nodeToAdd = new DirectoryNode(name, path);
        workingNode.addToList(nodeToAdd);
      } else {
        display.standardOutputLine("Improper path.");
      }
    } catch (Exception e) {
      return;
    }
  }

  /**
   * This method adds a file node in the tree to the path specified
   * 
   * @param path Path of a node needing to be added
   * @param data The text of the file node
   * @exception e If the path does not exist
   */
  public void addFileNode(String path, String data) {
    try {
      String[] pathList = path.split("/");
      String name = pathList[pathList.length - 1];
      DirectoryNode workingNode = getParent(path);
      if (workingNode != null) {
        path = this.getFullPath(path);
        FileNode nodeToAdd = new FileNode(name, path, data);
        workingNode.addToList(nodeToAdd);
      } else {
        display.standardOutputLine("Improper path.");
      }

    } catch (Exception e) {
      return;
    }
  }

  /**
   * Given a path to a node, this method returns the parent node of the node
   * 
   * @param path Path to a node
   * @return The parent node
   */
  private DirectoryNode getParent(String path) {
    DirectoryNode parent = null;
    path = getFullPath(path);
    int end_index = path.lastIndexOf('/');
    String parentPath;
    parentPath = path.substring(0, end_index);

    if (!parentPath.equals("")) {
      parent = getNodeForPath(parentPath);
    } else {
      parent = root;
    }
    return parent;
  }

  /**
   * This method gets the name of the parent node
   * 
   * @param path A path
   * @return The name of the parent node
   */
  public String getNameOfParent(String path) {
    DirectoryNode parent = getParent(path);
    return parent.getName();
  }


  /**
   * Given a path to a file node, this method returns the fileNode
   * 
   * @param path The path to a file node
   * @return A FileNode
   */
  private FileNode getFileNode(String path) {
    try {
      DirectoryNode parent = getParent(path);
      String[] pathList = path.split("/");
      int fileIndex = parent.obtainChildStringList().indexOf(pathList[pathList.length - 1]);
      FileNode file = (FileNode) parent.obtainList().get(fileIndex);
      return file;
    } catch (Exception e) {
      return null;
    }
  }


  /**
   * Purpose of method is to get the contents of the file at given path.
   * 
   * @param path Path of file
   * @return Contents of file
   * @exception e If path does not exist
   */
  public String getFileContents(String path) {
    try {
      FileNode file = getFileNode(path);
      return file.getData();
    } catch (Exception e) {
      return "Invalid file path";
    }
  }


  /**
   * Purpose of method is to remove the the file/directory, at the path specified, from the tree.
   * 
   * @param path Path of file
   * @exception e If path does not exist
   */
  public void removeNode(String path) {
    try {
      assert validateFileExistence(path) == true;
      DirectoryNode parent = getParent(path);
      String[] pathList = path.split("/");
      String name = pathList[pathList.length - 1];
      parent.removeNodeFromList(name);
    } catch (Exception e) {
      display.standardOutputLine("Node to remove does not exist");
    }
  }


  /**
   * Purpose of method is to retrieve list of all files/directories that the directory is storing
   * 
   * @param path Path of Directory
   * @return List of contents in Directory
   */
  public ArrayList<String> getFilesInDirectory(String path) {
    DirectoryNode dir = getNodeForPath(path);
    return dir.obtainChildStringList();
  }

  /**
   * This checks if the node at the given path is a directory node
   * 
   * @param path a path to a node
   * @return True/False depending on node type
   */
  public boolean isDirectory(String path) {
    try {
      DirectoryNode temp = this.getNodeForPath(path);
      ArrayList<String> tempList = temp.obtainChildStringList();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  // ensure that\
  /**
   * This method obtains the subTree
   * 
   * @param current The current node
   * @param start The starting node
   * @param paths The multiple different paths
   */
  private void getSubTree(DirectoryNode current, DirectoryNode start, ArrayList<String> paths) {
    ArrayList<Node> startChildrenList = start.obtainList();
    Node updatedChildNode = null;
    // add all children of start node
    for (Node childNode : startChildrenList) {
      if (childNode instanceof DirectoryNode) { // if dir then make node dir
        // contantenate name of current dir child with dest path
        updatedChildNode =
            new DirectoryNode(childNode.getName(), current.getPath() + "/" + childNode.getName());
      } else { // else make file node
        updatedChildNode = new FileNode(childNode.getName(),
            current.getPath() + "/" + childNode.getName(), ((FileNode) childNode).getData());
      }
      current.addToList(updatedChildNode);
      paths.add(childNode.getPath());
    }
    ArrayList<Node> currentChildrenList = current.obtainList();
    // move i up incase currentChildrenList already has children
    for (int i = currentChildrenList.size() - startChildrenList.size(); i < currentChildrenList
        .size(); i++) {
      // if child isDirectory then go into it
      if (currentChildrenList.get(i) instanceof DirectoryNode) {
        getSubTree((DirectoryNode) (currentChildrenList.get(i)), (DirectoryNode) (startChildrenList
            .get(i - (currentChildrenList.size() - startChildrenList.size()))), paths); // must move
                                                                                        // i back
      }
    }
  }

  /**
   * This method obtains the sub Directory of the tree
   * 
   * @param path path to now
   * 
   * @return
   */
  public ArrayList<String> getSubDirectories(String path) {
    ArrayList<String> subDirectories = new ArrayList<String>();
    DirectoryNode bogus = new DirectoryNode("", "");
    getSubTree(bogus, getNodeForPath(path), subDirectories);
    return subDirectories;
  }

  // get subTree from targer put it in dest
  /**
   * This method copy a sub part of the tree and moves it to a desired path
   * 
   * @param oldPath Path to old location
   * @param newPath Desired location to where folders/file are moved
   */
  public void copySubTree(String oldPath, String newPath) {
    ArrayList<String> bogus = new ArrayList<String>();
    getSubTree(getNodeForPath(newPath), getNodeForPath(oldPath), bogus);
  }

}
