package tree;

import java.util.ArrayList;

public interface ITree {
  public String recursiveTree(String path, int depth, String treeText);
  public void updateCWD(String path);
  public String getCWD();
  public boolean validateFileExistence(String path);
  public void addDirectoryNode(String path);
  public void addFileNode(String path, String data);
  public String getNameOfParent(String path);
  public String getFileContents(String path);
  public void removeNode(String path);
  public ArrayList<String> getFilesInDirectory(String path);
  public boolean isDirectory(String path);
  public ArrayList<String> getSubDirectories(String path);
  public void copySubTree(String oldPath, String newPath);
}
