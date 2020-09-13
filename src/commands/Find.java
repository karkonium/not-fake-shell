package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import tree.DirectoryNode;
import tree.Node;

/**
 * This class finds the path to a file or a folder
 *
 */
public class Find extends Commands {
  /**
   * This constructor for Find
   * 
   * @param args Takes in arguments to find
   */
  public Find(ArrayList<String> args) {
    arguments = args;
  }

  /**
   * This method completes the function of find command
   * 
   * @return the file/folder path found
   */
  public String execute() {
    if (this.validateArgs()) {
      output = executeFind();
      if (toRedirect()) {
        redirectionRequired();
        return "";
      }
    } else {
      display.standardOutputLine("Invalid arguments");
    }
    return output;
  }

  /**
   * This method finds the path to a file or folder
   * 
   * @return path to the file/folder
   */

  private String executeFind() {
    String out = "";;
    String name = arguments.get(arguments.indexOf("-name") + 1);
    name = name.substring(1, name.length() - 1);
    String type = arguments.get(arguments.indexOf("-name") - 1);
    boolean typeDir = false;
    if (type.equals("d"))
      typeDir = true;
    List<String> allPaths = arguments.subList(0, arguments.indexOf("-type"));
    for (String startPath : allPaths) {
      if (directory.validateFileExistence(startPath)) {
        ArrayList<String> possiblePaths = directory.getSubDirectories(startPath);
        for (String path : possiblePaths) {
          String[] pathArray = path.split("/");
          String pathName = pathArray[pathArray.length - 1];
          if (name.equals(pathName)) {
            if (directory.isDirectory(path) == typeDir)
              out += path + "  ";
          }
        }
      } else {
        display.standardOutputLine("Invalid Path: " + startPath);
      }
    }

    return out;
  }


  /**
   * Determines if arguments provided by user fit with the command signature
   * 
   * @param args list of all arguments for command
   * @return true if arguments are valid
   */

  protected boolean validateArgs(ArrayList<String> args) {
    if (!super.validateArgs())
      return false;
    if (!(args.size() >= 5 && args.contains("-type")))
      return false;
    int start = args.indexOf("-type");
    if (!(args.get(start + 1).equals("f") || args.get(start + 1).equals("d"))) {
      return false;
    } else if (!(args.get(start + 2)).equals("-name")) {
      return false;
    } else if (args.get(start + 3).length() > 2
        && !(args.get(start + 3).startsWith("\"") && args.get(start + 3).endsWith("\""))) {
      return false;
    } else if (start + 4 < args.size()) {
      if (!(start + 6 == args.size() && toRedirect()))
        return false;
    }
    return true;
  }

}
