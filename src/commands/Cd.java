package commands;

import java.util.ArrayList;
import java.util.Arrays;
import display.*;

/**
 * This class changes the directory depending on the command
 *
 */
public class Cd extends Commands {

  private static String methodDocumentation = "NAME: cd\nSYNOPSIS: cd DIR\nDESCRIPTION: Change "
      + "directory to DIR, which may be relative to the current directory or may be a full path. "
      + "As with Unix, .. means a parent directory and a .means the current directory. The"
      + " directory must be /, the forward slash. The foot of the file system is a single slash:"
      + "/.";

  /**
   * Constructor for Cd class, changes the current directory
   * 
   * @param args An ArrayList of arguments
   */
  public Cd(ArrayList<String> args) {
    arguments = args;
  }

  /**
   * This method will complete the function of Change directory(Cd)
   */
  public String execute() {
    if (validateArgs()) {
      changeDirectory(arguments.get(0));
    } else {
      display.standardOutputLine("Invalid input");
    }
    return output;
  }

  /**
   * Determines if arguments provided by user fit with the command signature
   * 
   * @param args list of all arguments for command
   * @return true if arguments are valid
   */
  protected boolean validateArgs() {
    // have to also check if the thing they wanna CD into is a file, then error msg
    if (!super.validateArgs())
      return false;
    if (!(arguments.size() == 1 || arguments.size() == 3))
      return false;
    if (!validator.validatePathName(arguments.get(0)))
      return false;
    return true;
  }


  /**
   * Takes in a path and changes the working directory to one requested by the user.
   * 
   * @param path Path to change current working directory to
   */
  public void changeDirectory(String path) {
    directory.updateCWD(path);
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
