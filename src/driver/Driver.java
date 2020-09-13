package driver;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Hashtable;
import commands.*;
import display.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


/**
 * This class is responsible for choosing the right command
 *
 */
public class Driver {

  private Hashtable<String, String> commandTable;
  private static Display display = new Display();

  /**
   * Constructor that initializes a hashtable table
   */
  public Driver() {
    initializeCommandTable();
  }

  /**
   * This method adds all the possible commands to a hashtable table
   */
  private void initializeCommandTable() {
    commandTable = new Hashtable<String, String>();
    commandTable.put("cat", "Cat");
    commandTable.put("ls", "Ls");
    commandTable.put("cd", "Cd");
    commandTable.put("echo", "Echo");
    commandTable.put("get", "Get");
    commandTable.put("man", "Man");
    commandTable.put("popd", "Popd");
    commandTable.put("mkdir", "MkDir");
    commandTable.put("pushd", "Pushd");
    commandTable.put("pwd", "Pwd");
    commandTable.put("history", "History");
    commandTable.put("cp", "Cp");
    commandTable.put("mv", "Mv");
    commandTable.put("tree", "TreeCommands");
    commandTable.put("save", "SaveState");
    commandTable.put("load", "LoadState");
    commandTable.put("find", "Find");
  }

  /**
   * This method calls the right command to call
   * 
   * @param name The command
   * @param args A list of arguments
   * @throws SecurityException
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   * @throws IllegalArgumentException
   * @return Returns if commands exist or not
   */
  public String chooseCommand(String name, ArrayList<String> args)
      throws InstantiationException, NoSuchMethodException, SecurityException,
      IllegalArgumentException, InvocationTargetException {
    try {
      String className = commandTable.get(name);
      Class<?> classInstance = Class.forName("commands." + className);
      try {
        Constructor<?> constructor = classInstance.getConstructor(ArrayList.class);
        Commands instance = (Commands) constructor.newInstance(args);
        display.standardOutputLine(((Commands) instance).execute());
        return commandTable.get(name) + " successful";
      } catch (InstantiationException e) {
        display.standardOutputLine("Command does not exist");
        return "Command does not exist";
      } catch (IllegalAccessException e) {
        display.standardOutputLine("Command does not exist");
        return "Command does not exist";
      }
    } catch (ClassNotFoundException e) {
      display.standardOutputLine("Command does not exist");
      return "Command does not exist";
    }
  }
}
