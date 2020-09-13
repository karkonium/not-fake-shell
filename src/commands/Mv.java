package commands;

import java.util.ArrayList;

/**
 * This class moves files/folders from one location to another
 */
public class Mv extends Cp {
  /**
   * Constructor
   * 
   * @param args Takes in the an arraylist
   */
  public Mv(ArrayList<String> args) {
    super(args);
  }

  /**
   * This method completes the function for the Mv command
   * 
   * @return returns string of command
   */
  public String execute() {
    super.execute();
    if (validateArgs()
        && !(directory.isDirectory(arguments.get(0)) && !directory.isDirectory(arguments.get(1))))
      directory.removeNode(arguments.get(0));
    return output;
  }
}
