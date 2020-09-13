package commands;

import java.util.ArrayList;
import java.util.List;
import display.*;


/**
 * This class creates a directory
 *
 */
public class MkDir extends Commands {

  private static String methodDocumentation = "NAME: mkdir\nSYNOPSIS: mkdir [Path...]\n"
      + "DESCRIPTION: Create directories, each of which may be relative to the current directory\n"
      + "or may be a full path.";



  /**
   * Constructor for Mkdir class, responsible for making a directory
   * 
   * @param args An arraylist of arguments
   */
  public MkDir(ArrayList<String> args) {
    arguments = args;
  }


  /**
   * This method completes the function of the mkdir command
   */
  public String execute() {
    if (validateArgs()) {
      if (arguments.size() > 2 && validator.checkSymbol(arguments.get(arguments.size() - 2))) {
        makeDirectories(arguments.subList(0, arguments.size() - 2));
      } else {
        makeDirectories(arguments);
      }
    }
    return output;
  }

  /**
   * Determines if arguments provided by user fit with the command signature
   * 
   * @return true if arguments are valid
   */
  protected boolean validateArgs() {
    if (!super.validateArgs())
      return false;
    if (!(arguments.size() > 0))
      return false;
    return true;
  }

  /**
   * Static method that returns the documentation for the specific command in a string format
   * 
   * @return Method documentation in string form
   */
  public static String getDocumentation() {
    return methodDocumentation;
  }


  /**
   * This method creates a directory at given paths
   * 
   * @param list An ArrayList of paths
   */
  public void makeDirectories(List<String> list) {
    if (list.size() != 0) {
      for (String path : list) {
        if (validator.validatePathName(path) && !directory.validateFileExistence(path)) {
          directory.addDirectoryNode(path);
        } else {
          output += (path + ": Cannot create Directory\n");
        }
      }
    } else {
      output += ("No directory given to make");
    }
  }

}
