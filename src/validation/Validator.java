package validation;

import java.io.File;
import display.*;

/**
 * This class validates the commands entered by the user
 *
 */
public class Validator {

  private static char illegalCharsFile[] =
      {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '{', '}', '~', '|', '<', '>', '?'};

  private static Display display = new Display();
  /**
   * Constructs validator object that is responsible for validating any argument given to it
   */
  public Validator() {}

  /**
   * This method is responsible for checking if the name given by user is valid and contains no
   * illegal characters.
   * 
   * @param name Determines name of file/directory given by user
   * @return Confirmation whether argument is valid (true)
   */
  private boolean validateName(String name) {
    for (int i = 0; i < name.length(); i++) {
      for (int j = 0; j < illegalCharsFile.length; j++) {
        if (name.charAt(i) == illegalCharsFile[j])
          return false;
      }
    }
    return true;
  }


  /**
   * This method checks whether the number inputed by user is a valid integer
   * 
   * @param number String form of number given by user
   * @return Confirmation whether argument is valid (true)
   */
  public boolean validateInt(String number) {

    try {
      Integer.parseInt(number);
    } catch (Exception e) {
      display.standardOutputLine("Excepion: Bad input. Argument not of type integer");
      return false;
    }
    return true;

  }
  
 

  /**
   * This method determines whether path given by user is valid
   * 
   * @param path Represents the path in question
   * @return Confirmation whether argument is valid (true)
   */
  public boolean validatePathName(String path) {
    String names[] = path.split("/");

    for (int k = 0; k < names.length; k++) {
      if (!(validateName(names[k]) || names[k].equals(".") || names[k].equals(".."))) {
        display.standardOutputLine("Exception: Bad input. Argument not valid path name");
        return false;
      }
    }
    return true;
  }

  /**
   * This method is responsible for checking if the path leading up to the file exist
   * @param pathToFile Path to the file
   * @return Confirms if the path up until the file exists
   */
  public boolean validateFilePath(String pathToFile) {
    String path[] = pathToFile.split("/");
    int length = path.length;
    String folderPath = "/";
    for (int i=0;i<length-1;i++) {
      folderPath = folderPath + "/" + path[i];
    }
    File folders = new File(folderPath);
    if ((path[length-1].endsWith(".txt")) && (folders.exists())) {
      return true;
    }
    return false;
  }
  
  /**
   * Method is responsible for checking whether the argument given by user is surrounded by quotes.
   * 
   * @param stringInput Determines input by user that needs to be validated
   * @return Confirmation whether argument is valid (true)
   */
  public boolean validateString(String stringInput) {
    try {
      if (stringInput.charAt(0) != '"' || stringInput.charAt(stringInput.length() - 1) != '"') {
        display.standardOutputLine("Excepion: Bad input. String is not quoted properly");
        return false;
      }
    } catch (Exception e) {
      display.standardOutputLine("Excepion: Bad input. String is not quoted properly");
      return false;
    }

    return true;
  }

  /**
   * Method is responsible for checking whether the argument given by user is a valid symbol.
   * displays error if not true
   * 
   * @param symbolInput is input by user that needs to be validated to symbol
   * @return Confirmation whether argument is valid (true)
   */
  public boolean validateSymbol(String symbolInput) {
    if (checkSymbol(symbolInput))
      return true;
    display.standardOutputLine("Excepion: Bad input. Argument not valid symbol (>> or >)");
    return false;
  }
  
  /**
   * Method is responsible for checking whether the argument given by user is a valid symbol.
   * 
   * @param symbolInput is input by user that needs to be validated to symbol
   * @return Confirmation whether argument is valid (true)
   */
  public boolean checkSymbol(String symbolInput) {
    return (symbolInput.equals(">>") || symbolInput.equals(">"));
  }
  
  public boolean checkOption(String optionInput) {
    return (optionInput.length() > 1 && optionInput.charAt(0) == '-');
  }
  
}
