package commands;

import java.util.ArrayList;
import java.util.Arrays;
import fileIO.*;

/**
 * This class is responsible for representing files
 *
 */
public class Echo extends Commands {

  private static String methodDocumentation = "NAME: echo\nSYNOPSIS: echo STRING [> OUTFILE]\n"
      + "DESCRIPTION: If OUTFILE not provided, display string to the shell, otherwise put "
      + "string\n into the file OUTFILE\nSYNOPSIS: echo STRING [>> OUTFILE]\nDESCRIPTION: File "
      + "must be given and instead of overwriting text it appends the text.";


  /**
   * Constructs object for echo creates/updates text files or print to console
   * 
   * @param name Determines name of the directory node
   */
  public Echo(ArrayList<String> args) {
    arguments = args;
  }


  /**
   * Method that will complete the function of Echo Command
   */
  public String execute() {

    if (validateArgs()) {
      output = arguments.get(0).substring(1, arguments.get(0).length() - 1);
      if (toRedirect()) {
        redirectionRequired();
        return "";
      }
    } else {
      display.standardOutput("Invalid input");
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
    if (!(arguments.size() == 1 || arguments.size() == 3))
      return false;
    if (!validator.validateString(arguments.get(0)))
      return false;
    if (arguments.size() == 3) {
      if (!validator.validateSymbol(arguments.get(1)))
        return false;
      if (!validator.validatePathName(arguments.get(2)))
        return false;
    }
    return true;

  }


  /**
   * Finds the correct placement for text given by user and performs the correct type of echo
   * 
   * @param textForFile text to be written onto file
   * @param typeOfEcho
   * @param path Path where file is located
   */

  public String getString(ArrayList<String> args) {
    // traverse through path given

    if (args.size() == 1) {
      return args.get(0);
    } else if (args.size() == 3) {
      return "Redirect: " + args.get(0);
    } else {
      return "Invalid number of arguments";
    }

  }


  /**
   * This method prints out the text in a file
   * 
   * @param textForFile Text in the file
   */

  public void displayString(String textForFile) {
    display.standardOutputLine(textForFile);
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

