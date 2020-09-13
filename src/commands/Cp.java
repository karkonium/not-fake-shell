package commands;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class copies file/folder onto a desired path
 *
 */
public class Cp extends Commands {
  public Cp(ArrayList<String> args) {
    arguments = args;
  }

  /**
   * Determines if arguments provided by user fit with the command signature
   * 
   * @return true if arguments are valid
   */
  protected boolean validateArgs() {
    if (!super.validateArgs())
      return false;
    if (!(arguments.size() == 2 || arguments.size() == 4))
      return false;
    if (!(directory.validateFileExistence(arguments.get(0))
        && directory.validateFileExistence(arguments.get(1))))
      return false;
    if (arguments.get(0).equals("/") || arguments.get(1).equals("/"))
      return false;
    if (arguments.size() == 4 && !toRedirect())
      return false;
    return true;
  }


  /**
   * Method that will complete the function of Cp Command
   * 
   * @return output of command
   */
  public String execute() {
    if (validateArgs()) {
      if (directory.isDirectory(arguments.get(0))) {
        if (directory.isDirectory(arguments.get(1))) {
          directory.copySubTree(arguments.get(0), arguments.get(1));
        } else {
          display.standardOutputLine("Invalid input - cannot copy directory into file");
        }
      } else {
        if (directory.isDirectory(arguments.get(1))) {
          String fileData = directory.getFileContents(arguments.get(0));
          String[] path = arguments.get(0).split("/");
          String fileName = path[path.length - 1];
          directory.addFileNode(arguments.get(1) + "/" + fileName, fileData);
        } else {
          String fileData = directory.getFileContents(arguments.get(0));
          directory.removeNode(arguments.get(1));
          directory.addFileNode(arguments.get(1), fileData);
        }
      }
    } else {
      display.standardOutputLine("Invalid arguments");
    }

    return output;
  }

}
