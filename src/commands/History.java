package commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import display.*;


/**
 * This class keeps track of the commands the user has entered, and depending on the command the
 * user can see the history
 *
 */
public class History extends Commands implements Serializable {

  private static ArrayList<String> commandList = new ArrayList<String>();
  private static String methodDocumentation = ("NAME: history\nSYNOPSIS: history [number]\n"
      + "DESCRIPTION: This command will print out recent commands, one command per line.");


  /**
   * Constructs history object and responsible for displaying appropriate history
   * 
   * @param args ArrayList of arguments
   */
  public History(ArrayList<String> args) {
    arguments = args;
  }

  /**
   * This method obtains the current history list
   * 
   * @return The current command list
   */
  public static ArrayList<String> getHistory() {
    return commandList;
  }

  /**
   * This method sets the command list to the history
   * 
   * @param history A command list
   */
  public static void setHistory(ArrayList<String> history) {
    commandList = history;
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
   * This method adds each command that is given by the user into the list that stores all commands
   * from beginning of runtime
   * 
   * @param command command given by user to be added to history log
   */
  public static void addCommand(String command) {
    commandList.add(command);
  }


  /**
   * Displays all history of commands
   */
  private String displayHistory() {
    String history = "";
    // loops through entire list of commands and prints them numbered
    for (int i = 0; i < commandList.size(); i++) {
      history =
          history + Integer.toString(i + 1) + ". " + commandList.get(i) + System.lineSeparator();
    }
    return history;
  }


  /**
   * Displays history of commands up until user specified
   * 
   * @param howRecent Determines how many most recent commands, will be displayed
   */
  private String displayHistory(int howRecent) {
    String history = "";
    if (howRecent > commandList.size()) {
      history = displayHistory();
      return history;
    }
    // loops through list of commands until number specified by user
    for (int i = commandList.size() - howRecent; i < commandList.size(); i++) {
      history =
          history + Integer.toString(i + 1) + ". " + commandList.get(i) + System.lineSeparator();
    }
    return history;
  }

  
  /**
   * Determines if arguments provided by user fit with the command signature
   * 
   * @return true if arguments are valid
   */
  protected boolean validateArgs() {
    if (!super.validateArgs())
      return false;
    if (!(arguments.size() == 0 || arguments.size() == 2 || arguments.size() == 1
        || arguments.size() == 3))
      return false;
    if ((arguments.size() == 3 || arguments.size() == 2) && !toRedirect())
      return false;
    return true;
  }


  @Override
  /**
   * This method completes the function of History command
   */
  public String execute() {
    if (!validateArgs()) {
      display.standardOutputLine("Invalid Input");
      return "";
    }
    int validArgs[] = {0, 1, 2, 3};
    validArgumentSizes = Arrays.copyOf(validArgs, 4);

    if (arguments.size() == 0 || arguments.size() == 2) {
      output = displayHistory();

    }

    else {
      if (!validator.validateInt(arguments.get(0)))
        return "invalid argument";
      output = displayHistory(Integer.parseInt(arguments.get(0)));
    }

    if (arguments.size() == 2 || arguments.size() == 3) {
      redirectType = arguments.get(arguments.size() - 2);
      outFile = arguments.get(arguments.size() - 1);
    }
    if (toRedirect())
      redirectionRequired();

    return output;
  }
}
