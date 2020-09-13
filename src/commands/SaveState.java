package commands;

import java.io.*;
import java.util.ArrayList;
import display.Display;
import tree.*;
import validation.*;
import commands.*;

public class SaveState extends Commands implements Serializable {

  private static String methodDocumentation =
      "NAME: save\nSYNOPSIS: save FILE\n" + "DESCRIPTION: Saves state of program into FILE";
  
  /**
   * Constructor 
   * @param paths A path to a text file 
   * @throws IOException
   */
  public SaveState(ArrayList<String> paths) throws IOException {
    arguments = paths;
  }

  /**
   * This method executes the program 
   */
  public String execute() {

    if (validateArgs() && validator.validateFilePath(arguments.get(0))) {

        save(arguments.get(0));
    
  } else {
	  display.standardOutputLine("Invalid arguments");
  }
  return "";
}
  
  /**
   * This method saves the current state of the program onto a given file path
   * @param filePath The path to a file  
   * @throws IOException
   */
  private void save(String filePath) {
    try {
      FileOutputStream outFile = new FileOutputStream(filePath);
      ObjectOutputStream outputStream = new ObjectOutputStream(outFile);
      outputStream.writeObject(this.directory);
      outputStream.writeObject(History.getHistory());
      outputStream.close();
    } catch (FileNotFoundException e) {
      display.standardOutputLine("Exception: Bad input. Argument not valid path name");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      display.standardOutputLine("Exception: Bad input. Argument not valid path name");
    }
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
   * Static method that returns the documentation for the specific command in a string format
   * 
   * @return Method documentation in string form
   */
  public static String getDocumentation() {
    return methodDocumentation;
  }
}
