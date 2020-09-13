package commands;

import java.util.ArrayList;
import display.*;

/**
 * This class prints the contents of a file or directory depending on the command
 *
 */
public class Ls extends Commands {

  private static String methodDocumentation = "NAME: ls\nSYNOPSIS: ls [Path...]\nDESCRIPTION:If "
      + "no paths are given, print the contents (file or directory) of the current directory, with"
      + " a new line following each of the content (file or directory). Otherwise, for each path p,"
      + " the order listed: If p specifies a file, print p. If p specifies a directory, print"
      + " p,  a colon, then the contents of that directory, then an extra new line. If p does not "
      + "exist, print a suitable message.";


  /**
   * Constructs object for Ls which is responsible for listing files/directories of given paths
   * 
   * @param args List of paths that the user wants contents of
   */
  public Ls(ArrayList<String> args) {
    arguments = args;
  }


  /**
   * This method prints out the directories
   * 
   * @param directories A List of directories to be printed
   */
  private void printDirectories(ArrayList<String> directories) {
    for (String dir : directories) {
      output += dir + "\n";
    }
  }
  /**
   * This method completes the function for Ls command
   */

  public String execute() {
    int size = arguments.size();
    if (size == 0) listDirectoriesOrFiles(directory.getCWD());
    if (toRedirect()) size -= 2;
    if ((size != 0) && !arguments.get(0).equals("-R")) {
      for (int i = 0; i < size; i++) {
        validator.validatePathName(arguments.get(i));
        listDirectoriesOrFiles(arguments.get(i));
      }
    }
    if ((size != 0) && arguments.get(0).equals("-R")) {
      int p = 0;
      if (size == 1) p = 1;
      for (int i = 0; i < size; i++) {
        validator.validatePathName(arguments.get(i));
        if (p == 1 || directory.validateFileExistence(arguments.get(i))) {
          ArrayList<String> subDir = new ArrayList<String>();
          if (size != 1) {
            subDir = directory.getSubDirectories(arguments.get(i));
            listDirectoriesOrFiles(arguments.get(i));
          } else 
            subDir = directory.getSubDirectories(directory.getCWD());
          for (String path : subDir) {
            listDirectoriesOrFiles(path);
          }}}}
    if (toRedirect()) {
    	redirectionRequired();
    	output = "";
    }
    return output;
  }


  /**
   * Method displays everything in the directory for the path given
   * 
   * @param path Determines path of directory
   */
  public void listDirectoriesOrFiles(String path) {
    if (directory.validateFileExistence(path)) {
      ArrayList<String> files;
      if (directory.isDirectory(path)) {
        if (path != directory.getCWD())
          output += (path + ": \n");
        files = directory.getFilesInDirectory(path);
        printDirectories(files);
      } else {
        output += path + "\n";
      }
    } else {
      display.standardOutput(path + " :Invalid path\n");
    }
  }


  /**
   * Static method that returns the documentation for the specific command in a string format
   * 
   * @return Method documentation in string form
   */
  public static String getDocumentation() {
    return methodDocumentation;
  }
}
