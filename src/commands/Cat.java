package commands;

import java.util.ArrayList;
import java.util.List;
import display.*;

/**
 * This class displays the contents of a file or files
 *
 */
public class Cat extends Commands {

  private static String methodDocumentation = "NAME: cat\nSYNOPSIS: cat FILE1 [FILE2 ...]\n"
      + "DESCRIPTION: " + "display the contents of FILE1 and other files (i.e. File2 ....).";

  /**
   * Constructs an object which performs the cat command using the ArrayList of paths given for each
   * file.
   * 
   * @param filesToDisplay list of files who's contents must be displayed
   */
  public Cat(ArrayList<String> args) {

    arguments = args;

  }

  /**
   * Method that will complete the function of Cat Command
   */
  public String execute() {
    if (validateArgs()) {
      if (toRedirect()) {
        output = displayFileContents(arguments.subList(0, arguments.size() - 2));
        redirectionRequired();
        return "";
      } else {
        output = displayFileContents(arguments);
      }
    } else {
      display.standardOutputLine("Invalid input");
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
    if (validator.checkSymbol(arguments.get(0)))
      return false;

    return true;
  }


  /**
   * The purpose of this method is that it takes an ArrayList of paths for all files that content
   * needs to be displayed for, and returns the string for it
   * 
   * @param list List of paths for all files
   * @return string with contents of all files
   */
  public String displayFileContents(List<String> list) {
    String txt = "";

    for (int i = 0; i < list.size(); i++) {
      if (directory.validateFileExistence(list.get(i)) && !directory.isDirectory(list.get(i))) {
        txt = txt + directory.getFileContents(list.get(i));
        if (list.size() == 1) {
          txt = txt + "\n";
        } else if (list.size() > 1 && i != list.size() - 1) {
          txt = txt + "\n\n\n\n";
        }
      } else {
        display.standardOutputLine((list.get(i) + ": Invalid file path"));
      }
    }
    return txt;

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
