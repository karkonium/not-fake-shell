package commands;

import java.io.*;
import java.util.ArrayList;
import javax.xml.bind.Validator;
import display.Display;
import tree.*;
import commands.Commands;
/**
 * This class loads up previous states of the program that were saved onto a file
 *
 */
public class LoadState extends Commands implements Serializable {

  private static String methodDocumentation =
      "NAME: load\nSYNOPSIS: load FILE\n" + "DESCRIPTION: Load state of program as saved on FILE";

  /**
   * The constructor
   * @param args A list of paths to a file
   * @throws ClassNotFoundException
   * @throws IOException
   */
  public LoadState(ArrayList<String> args) throws ClassNotFoundException, IOException {
    arguments = args;
  }
  /**
   * This method completes the function for Load command
   * @return An empty string
   */

  public String execute() {
      if (validateArgs() && validator.validateFilePath(arguments.get(0))) {
        try {
          load(arguments.get(0));
        } catch (ClassNotFoundException e) {
        } catch (IOException e) {
          display.standardOutputLine("Exception: Bad input. Argument not valid path name");
        }

      } else {
    	  display.standardOutputLine("Invalid input");
      }
 

    return "";
  }

  /**
   * Determines if arguments provided by user fit with the command signature
   * @return true if arguments are valid
   */
  protected boolean validateArgs() {
        //have to also check if the thing they wanna CD into is a file, then error msg
        if (!super.validateArgs()) 
            return false;
        if (!(arguments.size() == 1 || arguments.size() == 3))
          return false;
        if (!validator.validatePathName(arguments.get(0)))
          return false;
        return true;
      }
  
  /**
   * This method loads up previous states from a file
   * @param location The path to the file 
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public void load(String location) throws IOException, ClassNotFoundException {

    try {
      FileInputStream filein = new FileInputStream(location);
      ObjectInputStream in = new ObjectInputStream(filein);
      this.directory = (Tree) in.readObject();
      ArrayList<String> history = (ArrayList<String>) in.readObject();
      History.setHistory(history);
      in.close();
      filein.close();
    } catch (FileNotFoundException e) {
        return;
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
