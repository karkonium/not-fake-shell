package commands;

import java.util.ArrayList;
import display.*;
import fileIO.*;
import tree.*;
import validation.*;

/**
 * This class is responsible for creating the tree and holds main methods used by all commands
 *
 */
public abstract class Commands {

  protected static String methodDocumentation = "";
  protected static ParentTree directory;

  protected static Validator validator = new Validator();
  protected static FileIO fileReader = new FileIO();
  protected static Display display = new Display();

  /**
   * Determines arguments for command
   */
  protected ArrayList<String> arguments;
  /**
   * Determines size of args
   */
  protected int argSize;

  /**
   * Determines flags for output of command, if there is redirection and if outfile should be
   * created
   */
  protected String output = "";
  protected String redirectType = "";
  protected String outFile = "";

  protected ArrayList<String> sigature = new ArrayList<>();


  /**
   * This method gets the output
   * 
   * @return Output
   */
  public String getOutput() {
    return output;
  }

  /**
   * Gets arguments of command
   * 
   * @return arguments of command
   */
  public ArrayList<String> getArgs() {
    return arguments;
  };

  /**
   * To implement in every command
   */
  public abstract String execute();

  /**
   * Variable that determines number of arguments that are valid for each command
   */
  protected int[] validArgumentSizes;


  /**
   * The purpose of this method is to take a tree parameter and initialize class variable of
   * directory to it.
   * 
   * @param the tree that determines the directory for the class
   */
  public static void setDir(ParentTree dir) {
    directory = dir;
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
   * Validates arguments that user gives for command
   * 
   * @return true or false depending on if they are valid
   */
  protected boolean validateArgs() {
    if (arguments.contains(">") && arguments.contains(">>"))
      return false;
    String[] symbols = {">", ">>"};
    for (String symbol : symbols)
      if (arguments.contains(symbol)) {
        if (!(arguments.indexOf(symbol) == arguments.size() - 2))
          return false;
      }
    return true;
  }


  /**
   * Purpose of this method is to check if number of arguments is appropriate for the command being
   * called.
   * 
   * @return A true or false to validate whether arguments are valid
   */
  protected boolean validateNumArgs() {
    if (this.validArgumentSizes.length == 0)
      return true;

    for (int i = 0; i < this.validArgumentSizes.length; i++) {
      if (arguments.size() == validArgumentSizes[i])
        return true;
    }

    return false;
  }


  /**
   * This method redirects the command
   */
  public void redirectionRequired() {
    if (redirectType.equals("")) {
      display.standardOutputLine(output);
      return;
    }

    if (!outFile.equals("") && !directory.validateFileExistence(outFile)) {
      directory.addFileNode(outFile, output);
    }
    // find correct placement for it like in the path given
    else {
      if (redirectType.equals(">")) {
        fileReader.writeOntoFile(outFile, output);
      } else if (redirectType.equals(">>")) {
        fileReader.appendToFile(outFile, output);
      } else if (redirectType.equals("")) {

      }
    }
  }

  /**
   * Purpose of this method is to check whether the output of a command should be redirected to
   * another file.
   * 
   * @param ArrayList of arguments for the command instance
   * 
   * @return true if redirection is required
   */
  protected boolean toRedirect() {
    try {
      int size = arguments.size();
      if (validator.checkSymbol(arguments.get(size - 2))) {
        redirectType = arguments.get(size - 2);
        outFile = arguments.get(size - 1);
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }
}
