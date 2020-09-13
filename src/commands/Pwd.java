package commands;

import java.util.ArrayList;
import display.*;

/**
 * This class prints the current working directory
 *
 */
public class Pwd extends Commands {

  private static String methodDocumentation =
      "NAME: pwd\nSYNOPSIS: pwd\nDESCRIPTION:Print the current "
          + "working directory including the whole path";

  /**
   * Constructs object that performs the task of printing the current working directory that the
   * user is in.
   */
  public Pwd(ArrayList<String> args) {
    arguments = args;
  }

  /**
   * Determines if arguments provided by user fit with the command signature
   * 
   * @return true if arguments are valid
   */
  public boolean validateArgs() {
    if (!(arguments.size() == 0 || arguments.size() == 2))
      return false;
    if (arguments.size() == 2 && !(validator.checkSymbol(arguments.get(0))))
      return false;
    return true;
  }

  /**
   * This method completes the function for pwd command
   * 
   * @return current working directory
   */
  public String execute() {
    if (validateArgs()) {
      output = directory.getCWD();
      if (toRedirect()) {
        redirectionRequired();
      }
    } else {
      output = "Invalid Input";
    }
    return output;
  }

  /**
   * This method gets the current working directory
   * 
   * @return the current working directory
   */
  public String getPWD() {
    return directory.getCWD();
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
