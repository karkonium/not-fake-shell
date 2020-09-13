package commands;

import java.util.ArrayList;
import java.util.Stack;

import display.Display;

/**
 * This class pushes a directory into a current stack, moving the current working directory one
 * stack ahead
 *
 */
public class Pushd extends Commands {

  private static String methodDocumentation = "NAME: pushd\nSYNOPSIS: pushd DIR\nDESCRIPTION: "
      + "Saves the current working directory by pushing onto directory stack and then changes "
      + "the new current working directory to DIR. ";


  /**
   * This variable keep track of the stack
   */
  public static Stack<String> dirStack;

  /**
   * Constructor for Pushd, activates the command to push current directory into stack and change
   * current directory
   * 
   * @param path Determines path to push
   */
  public Pushd(ArrayList<String> path) {
      arguments = path;
  }
  
  /**
   * This method completes the function for Push command
   */
  public String execute() {
	if (validateArgs()) {
		dirStack.push(directory.getCWD());
	    Cd changeDirectory = new Cd(arguments);
	    changeDirectory.execute();
	} else {
		display.standardOutputLine("Invalid input");
	}
    return "";
  }
  /**
   * Determines if arguments provided by user fit with the command signature
   * 
   * @return true if arguments are valid
   */
  protected boolean validateArgs() {
	  if (!super.validateArgs())
		  return false;
	  if (!(arguments.size() == 1 || arguments.size() == 3)) 
		  return false;
	  if(!(directory.validateFileExistence(arguments.get(0)) && directory.isDirectory(arguments.get(0))))
		  return false;
	  if (!(arguments.size() == 3 && validator.validateSymbol(arguments.get(1))))
		  return false;
	  return true;		   
  }
  /**
   * This method sets the Stack
   * 
   * @param dirStack Stack
   */
  public static void setStack(Stack<String> dirStack) {
    Pushd.dirStack = dirStack;
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
