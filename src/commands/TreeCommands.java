package commands;

import java.util.ArrayList;
import java.util.Arrays;
import display.Display;

public class TreeCommands extends Commands {

  private static String methodDocumentation =
      "NAME: tree\nSYNOPSIS: tree\n" + "DESCRIPTION: Displays everything in current file system.";

  /**
   * Constructor
   * 
   * @param args Takes in
   */
  public TreeCommands(ArrayList<String> args) {
    arguments = args;
  }

  /**
   * This method completes the function for TreeCommands
   * 
   * @return returns command string
   */
  public String execute() {
    output = executeTree();
    if (toRedirect()) {
      redirectionRequired();
      return "";
    }
    return output;
  }

  /**
   * This method a Tree after checking if arguments are valid
   * 
   * @return A tree
   */
  private String executeTree() {
    String tree = "";
    if (validateArgs()) {
      tree += "/" + System.lineSeparator();
      tree += (directory.recursiveTree("/", 0, ""));
    } else {
      tree = ("Invalid number of arguments");
    }
    return tree;
  }

  /**
   * Determines if arguments provided by user fit with the command signature
   * 
   * @return true if arguments are valid
   */
  protected boolean validateArgs() {
    if (!super.validateArgs())
      return false;
    if (!(arguments.size() == 0 || arguments.size() == 2))
      return false;
    if (arguments.size() == 2 && !(validator.validateSymbol(arguments.get(0))
        && validator.validatePathName(arguments.get(1))))
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
}
