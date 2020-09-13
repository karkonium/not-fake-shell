package fileIO;

import display.Display;
import tree.*;

/**
 * This class has the properties of a file i.e reading, writing, and clearing
 *
 */
public class FileIO {

  private static ParentTree directory = null;
  private static Display display = new Display();


  /**
   * This method sets the directory
   * 
   * @param directory A tree directory
   */
  public void setDirectory(ParentTree directory) {
    FileIO.directory = directory;
  }


  /**
   * This method returns a file's contents
   * 
   * @param filePath Where the file is located
   * @return the file content
   */
  public String readfilePath(String filePath) {
    return directory.getFileContents(filePath);
  }


  /**
   * This method adds String input onto a file
   * 
   * @param filePath The file
   * @param input String to be added
   */
  public void appendToFile(String filePath, String input) {
    String oldString = directory.getFileContents(filePath) + System.lineSeparator();
    String stringToAdd = oldString.substring(0, oldString.length()) + input;
    writeOntoFile(filePath, stringToAdd);
  }


  /**
   * This method clears all the content in a file
   * 
   * @param filePath Where the file is located
   */
  public void clearFileContent(String filePath) {
    directory.removeNode(filePath);
    directory.addFileNode(filePath, "");
  }


  /**
   * This method writes onto a file
   * 
   * @param filePath Where the file is located
   * @param input String to be written
   */
  public void writeOntoFile(String filePath, String input) {
    directory.removeNode(filePath);

    // convert to newline
    String temp[] = input.split("\\\\n");
    String c = temp[0];

    for (int i = 1; i < temp.length; i++) {
      c += temp[i];
    }
    if (!directory.isDirectory(filePath)) {
      directory.addFileNode(filePath, c);
    } else {
      display.standardOutputLine("Invalid Path");

    }

  }
}
