package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;
import display.*;

/**
 * This class prints the documentation for each command
 *
 */
public class Man extends Commands {

  private static String methodDocumentation =
      "NAME: man CMD\n SYNOPSIS: man CMD\n DESCRIPTION: " + "Print documentation for CMD";


  /**
   * Constructs object that declares Man command calls on execute method
   * 
   * @param args A list of arguments
   */
  public Man(ArrayList<String> args) {
    arguments = args;
    int validArgs[] = {1, 3};
    validArgumentSizes = Arrays.copyOf(validArgs, 1);
    if (!IntStream.of(validArgs).anyMatch(n -> n == args.size())) {
      display.standardOutputLine("Invalid man input");
    } else {

    }
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
    if (arguments.size() == 3 && !toRedirect())
      return false;
    return true;
  }

  /**
   * This method completes the function for Man command
   * 
   * @return The documentation for a command
   */
  public String execute() {
    if (!validateArgs()) {
      display.standardOutputLine("Invalid input");
      return "";
    }
    output = Man.getCommandManual(arguments.get(0));
    if (arguments.size() == 3) {
      redirectType = arguments.get(1);
      outFile = arguments.get(2);
      redirectionRequired();
      return "";
    }

    return output;

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
   * Purpose of method is to return the documentation for any given command as requested by the
   * user.
   * 
   * @param command Determines command for which documentation is printed
   * @exception e if command is not valid
   */
  private static String getCommandManual(String command) {
    HashMap<String, String> commandsMap = new HashMap<String, String>();
    commandsMap.put("history", History.getDocumentation());
    commandsMap.put("man", Man.getDocumentation());
    commandsMap.put("cd", Cd.getDocumentation());
    commandsMap.put("cat", Cat.getDocumentation());
    commandsMap.put("mkdir", MkDir.getDocumentation());
    commandsMap.put("echo", Echo.getDocumentation());
    commandsMap.put("ls", Ls.getDocumentation());
    commandsMap.put("popd", Popd.getDocumentation());
    commandsMap.put("pushd", Pushd.getDocumentation());
    commandsMap.put("pwd", Pwd.getDocumentation());
    commandsMap.put("get", Get.getDocumentation());
    commandsMap.put("find", Find.getDocumentation());
    commandsMap.put("save", SaveState.getDocumentation());
    commandsMap.put("load", LoadState.getDocumentation());
    commandsMap.put("cp", Cp.getDocumentation());
    commandsMap.put("mv", Mv.getDocumentation());
    commandsMap.put("tree", TreeCommands.getDocumentation());


    if (commandsMap.containsKey(command)) {
      return commandsMap.get(command);
    } else
      return (command + " is not an existing command");
  }
}

