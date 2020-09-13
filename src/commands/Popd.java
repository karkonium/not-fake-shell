package commands;

import java.util.ArrayList;
import java.util.Stack;
import display.*;

/**
 * This class pops the last directory in the stack
 *
 */
public class Popd extends Commands {

  private static String methodDocumentation = "NAME: popd\nSYNOPSIS: popd\nDESCRIPTION: Remove "
      + "the top entry from the directory stack, and change directory into it. If no directory "
      + "on stack, then error message is shown.";


  /**
   * Variable to keep track of Stack
   */
  public static Stack<String> dirStack;


  /**
   * Default constructor
   */
  public Popd(ArrayList<String> arguments) {
    this.arguments = arguments;
  }

  /**
   * This method completes the function for Popd command
   * 
   * @return 
   */
  public String execute() {
    if (arguments.size() == 0
        || (arguments.size() == 2 && validator.checkSymbol(arguments.get(0)))) {
      popDir();
    } else {
      display.standardOutputLine("Invalid input");
    }
    return "";
  }

  /**
   * This method updates the stack to the current stack
   * 
   * @param dirStack Current stack
   */
  public static void setStack(Stack<String> dirStack) {
    Popd.dirStack = dirStack;
  }


  /**
   * This method pops the last item in the stack
   */
  private void popDir() {
    if (dirStack.size() == 0) {
      // Print error
      display.standardOutputLine("Stack is empty");
    } else {
      String newCWD = dirStack.pop();
      ArrayList<String> temp = new ArrayList<String>();
      temp.add(newCWD);
      Cd changeDir = new Cd(temp);
      changeDir.execute();
    }
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
